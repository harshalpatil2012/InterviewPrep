
Implment in stadlone mode Implement an application-level circuit breaker
class CircuitBreaker {
    public <T> T execute(Supplier<T> supplier) {
        // TODO: add circuit breaker functionality
        return supplier.get();
    }
}
Requirements:
        •    Count errors within a given time period, if the number of errors in the period exceeds or reaches a configurable level then the circuit breaker should open.
        •    When the circuit breaker is open, this means that all executions should fail fast.
        •    After a configurable period of time, the circuit breaker should close. This means that executions will no longer fail fast.




import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    // Circuit Breaker for external service calls
    private final CircuitBreaker userServiceCircuitBreaker;

    // Rest Template for making external API calls
    private final RestTemplate restTemplate;

    public UserController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;

        // Configure circuit breaker for user service
        this.userServiceCircuitBreaker = CircuitBreaker.newBuilder()
                .failureThreshold(3)  // Open circuit after 3 consecutive failures
                .slidingWindowDuration(Duration.ofMinutes(1))
                .openToHalfOpenTimeout(Duration.ofSeconds(30))
                .build();
    }

    @GetMapping
    public List<User> getAllUsers() {
        try {
            // Use circuit breaker to protect external service call
            return userServiceCircuitBreaker.execute(() -> {
                // Simulated external service call
                return fetchUsersFromExternalService();
            });
        } catch (CircuitBreaker.CircuitBreakerOpenException e) {
            // Fallback mechanism when circuit is open
            return getFallbackUsers();
        }
    }

    /**
     * Fetch users from an external service
     */
    private List<User> fetchUsersFromExternalService() {
        try {
            // Simulated risky external API call
            String userServiceUrl = "https://api.example.com/users";
            User[] users = restTemplate.getForObject(userServiceUrl, User[].class);
            return users != null ? List.of(users) : Collections.emptyList();
        } catch (Exception e) {
            // Log the error
            System.err.println("Error fetching users: " + e.getMessage());
            throw e;  // Rethrow to trigger circuit breaker
        }
    }

    /**
     * Fallback method when external service is unavailable
     */
    private List<User> getFallbackUsers() {
        // Return cached or default users
        return Collections.singletonList(
                new User(0L, "Default User", "default@example.com")
        );
    }

    // Additional endpoint to check circuit breaker state
    @GetMapping("/circuit-status")
    public CircuitBreakerStatus getCircuitStatus() {
        return new CircuitBreakerStatus(
                userServiceCircuitBreaker.getCurrentState().name(),
                userServiceCircuitBreaker.getErrorCount(),
                userServiceCircuitBreaker.getTotalCount()
        );
    }

    // Nested classes for demonstration
    public static class User {
        private Long id;
        private String name;
        private String email;

        // Constructor
        public User(Long id, String name, String email) {
            this.id = id;
            this.name = name;
            this.email = email;
        }

        // Getters and setters
        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }
    }

    // DTO for circuit breaker status
    public static class CircuitBreakerStatus {
        private String currentState;
        private int errorCount;
        private int totalCount;

        public CircuitBreakerStatus(String currentState, int errorCount, int totalCount) {
            this.currentState = currentState;
            this.errorCount = errorCount;
            this.totalCount = totalCount;
        }

        // Getters
        public String getCurrentState() {
            return currentState;
        }

        public int getErrorCount() {
            return errorCount;
        }

        public int getTotalCount() {
            return totalCount;
        }
    }
}