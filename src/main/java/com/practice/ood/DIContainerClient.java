package com.practice.ood;

import java.util.HashMap;
import java.util.Map;

// Sample Service interface
interface Service {
	void doSomething();
}

// Sample Service implementation
class ServiceImpl implements Service {
	@Override
	public void doSomething() {
		System.out.println("Service is doing something!");
	}
}

// Custom DI Container
class DIContainer {
	private Map<Class<?>, Object> services = new HashMap<>();

	// Register a service with its implementation in the DI container
	<T> void register(Class<T> serviceClass, T serviceInstance) {
		services.put(serviceClass, serviceInstance);
	}

	// Get an instance of the specified service class from the DI container
	<T> T resolve(Class<T> serviceClass) {
		Object serviceInstance = services.get(serviceClass);
		if (serviceInstance == null) {
			throw new IllegalArgumentException("Service not found in DI container: " + serviceClass.getName());
		}
		return serviceClass.cast(serviceInstance);
	}
}

// Client code using the custom DI Container
public class DIContainerClient {
	public static void main(String[] args) {
		// Create an instance of the custom DI container
		DIContainer container = new DIContainer();

		// Register the ServiceImpl as the implementation of the Service interface
		Service service = new ServiceImpl();
		container.register(Service.class, service);

		// Resolve the Service from the DI container and use it
		Service resolvedService = container.resolve(Service.class);
		resolvedService.doSomething();
	}
}
