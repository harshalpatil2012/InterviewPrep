package com.practice.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Design solution to process big log like 100gb file using java
 * 
 * Processing a 100GB log file using Java requires careful consideration of
 * memory usage and performance optimization. Here's a high-level design for a
 * solution to process such a file:
 * 
 * Divide the log file into smaller chunks: To avoid running out of memory, we
 * can break down the file into smaller pieces, such as 1GB or 2GB chunks, using
 * a divide-and-conquer approach.
 * 
 * Process each chunk separately: Once the file has been split into smaller
 * chunks, we can process each chunk individually. We can use multi-threading to
 * process the chunks in parallel to improve performance.
 * 
 * Use a buffer to read and write data: Reading and writing to disk can be a
 * bottleneck in processing large files. To mitigate this, we can use a buffer
 * to read and write data in chunks, rather than reading and writing one line at
 * a time.
 * 
 * Use data structures to store and analyze data: We can use various data
 * structures to store and analyze the log data. For example, we can use a
 * HashMap to count the frequency of each log message, or a PriorityQueue to
 * identify the most frequently occurring log messages.
 * 
 * Write output to disk: Once the log data has been processed, we can write the
 * output to a separate file or database for further analysis or reporting.
 * 
 * 
 * In this example, we read in chunks of 2MB using a buffer and process each
 * chunk by counting the frequency of each log message. The output is written to
 * a separate file using a FileWriter. Note that this is a simplified example
 * and may not be suitable for all use cases, but it should give you an idea of
 * how to approach processing large log files in Java.
 * 
 * @author harshal
 *
 */
public class LogProcessor {
	private static final int CHUNK_SIZE = 2 * 1024 * 1024; // 2MB

	public static void main(String[] args) throws IOException {
		String inputFilePath = args[0];
		String outputFilePath = args[1];

		try (FileReader fileReader = new FileReader(inputFilePath);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				FileWriter fileWriter = new FileWriter(outputFilePath)) {

			char[] buffer = new char[CHUNK_SIZE];
			int read;
			while ((read = bufferedReader.read(buffer)) > 0) {
				String chunk = new String(buffer, 0, read);

				// Process the chunk of log data
				Map<String, Integer> messageCounts = processLogChunk(chunk);

				// Write the output to disk
				for (Map.Entry<String, Integer> entry : messageCounts.entrySet()) {
					fileWriter.write(entry.getKey() + ": " + entry.getValue() + "\n");
				}
			}
		}
	}

	private static Map<String, Integer> processLogChunk(String chunk) {
		Map<String, Integer> messageCounts = new HashMap<>();

		// Process the log messages in the chunk
		String[] messages = chunk.split("\n");
		for (String message : messages) {
			// Count the frequency of each log message
			if (messageCounts.containsKey(message)) {
				messageCounts.put(message, messageCounts.get(message) + 1);
			} else {
				messageCounts.put(message, 1);
			}
		}

		return messageCounts;
	}
}
