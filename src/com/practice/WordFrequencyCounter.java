package com.practice;

import java.io.*;
import java.util.*;

/**
 * One storage disk has a set of text files. Each file is huge 1+GB in size. A
 * query file is provided having a list of words. Program has to parse through
 * the text files and compute frequency of words found from the query file
 * 
 * @author harshal
 *
 */

public class WordFrequencyCounter {

	// Define the size of the chunks to read from the text files
	private static final int CHUNK_SIZE = 4096;

	// Define the maximum number of words to output
	private static final int MAX_OUTPUT = 10;

	public static void main(String[] args) throws IOException {

		// Parse the query file and store the unique words in a set
		Set<String> queryWords = parseQueryFile("query.txt");

		// Initialize a hash table to store the frequency counts
		Map<String, Integer> freqCounts = new HashMap<>();

		// Process each text file
		for (String fileName : args) {
			processTextFile(fileName, queryWords, freqCounts);
		}

		// Sort the words by their frequency counts in descending order
		List<Map.Entry<String, Integer>> wordFreqList = new ArrayList<>(freqCounts.entrySet());
		wordFreqList.sort(Collections.reverseOrder(Map.Entry.comparingByValue()));

		// Output the top-k frequent words
		int k = Math.min(MAX_OUTPUT, wordFreqList.size());
		for (int i = 0; i < k; i++) {
			System.out.println(wordFreqList.get(i).getKey() + " : " + wordFreqList.get(i).getValue());
		}
	}

	private static Set<String> parseQueryFile(String fileName) throws IOException {
		Set<String> queryWords = new HashSet<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] words = line.split("\\s+");
				for (String word : words) {
					queryWords.add(word);
				}
			}
		}
		return queryWords;
	}

	private static void processTextFile(String fileName, Set<String> queryWords, Map<String, Integer> freqCounts)
			throws IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = reader.readLine()) != null) {
				processTextChunk(line, queryWords, freqCounts);
			}
		}
	}

	private static void processTextChunk(String chunk, Set<String> queryWords, Map<String, Integer> freqCounts) {
		String[] words = chunk.split("\\s+");
		for (String word : words) {
			if (queryWords.contains(word)) {
				freqCounts.put(word, freqCounts.getOrDefault(word, 0) + 1);
			}
		}
	}
}
