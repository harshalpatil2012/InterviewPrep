import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;


class Request {
    int arrival;
    int burstTime;

    Request(int arrival, int burstTime) {
        this.arrival = arrival;
        this.burstTime = burstTime;
    }
}ElementType

class Result {

    /*
     * Complete the 'getServerIndex' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER_ARRAY arrival
     *  3. INTEGER_ARRAY burstTime
     */

public static List<Integer> getServerIndex(int n, List<Integer> arrival, List<Integer> burstTime) {
        int serverCount = n;
        List<Integer> serverAvailableTime = new ArrayList<>(Collections.nCopies(serverCount, 0));
        List<Integer> serverAssigned = new ArrayList<>(Collections.nCopies(arrival.size(), -1));

        for (int i = 0; i < n; i++) {
            int chosenServer = -1;
            int minAvailableTime = Integer.MAX_VALUE;
            
            for (int server = 0; server < serverCount; server++) {
                if (serverAvailableTime.get(server) <= arrival.get(i)) {
                    if (serverAvailableTime.get(server) + burstTime.get(i) < minAvailableTime) {
                        chosenServer = server;
                        minAvailableTime = serverAvailableTime.get(server) + burstTime.get(i);
                    }
                }
            }
            
            if (chosenServer != -1) {
                serverAvailableTime.set(chosenServer, minAvailableTime);
                serverAssigned.set(i, chosenServer);
            }
        }
        return serverAssigned;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        int arrivalCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arrival = IntStream.range(0, arrivalCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        int burstTimeCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> burstTime = IntStream.range(0, burstTimeCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        List<Integer> result = Result.getServerIndex(n, arrival, burstTime);

        bufferedWriter.write(
            result.stream()
                .map(Object::toString)
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}