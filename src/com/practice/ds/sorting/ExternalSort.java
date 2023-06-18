package com.practice.ds.sorting;

/** 
 * Problem : Sorting 1 million 32 bit integers stored in a
 *  file with a maximum of 1MB RAM for data area of your program.
 */

import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.util.*;

public class ExternalSort {

	private static final int BLOCK_SIZE = 250000; // max number of integers that can fit in 1MB RAM

	public static void main(String[] args) throws IOException {
		String inputFilename = "input.txt";
		String outputFilename = "output.txt";
		File inputFile = new File(inputFilename);
		File outputFile = new File(outputFilename);
		int fileSize = (int) inputFile.length();

		// Divide the input file into smaller blocks
		int numBlocks = (int) Math.ceil((double) fileSize / (BLOCK_SIZE * 4));
		List<File> blockFiles = new ArrayList<File>();
		ByteBuffer buffer = ByteBuffer.allocate(BLOCK_SIZE * 4);
		try (FileChannel inputChannel = new FileInputStream(inputFile).getChannel()) {
			for (int i = 0; i < numBlocks; i++) {
				File blockFile = File.createTempFile("block", ".tmp");
				blockFile.deleteOnExit();
				try (FileChannel blockChannel = new FileOutputStream(blockFile).getChannel()) {
					int bytesRead = inputChannel.read(buffer);
					buffer.flip();
					while (bytesRead > 0) {
						blockChannel.write(buffer);
						buffer.clear();
						bytesRead = inputChannel.read(buffer);
						buffer.flip();
					}
				}
				blockFiles.add(blockFile);
			}
		}

		// Sort each block using Arrays.sort
		List<int[]> blocks = new ArrayList<int[]>();
		for (File blockFile : blockFiles) {
			try (FileChannel blockChannel = new FileInputStream(blockFile).getChannel()) {
				buffer.clear();
				int[] block = new int[BLOCK_SIZE];
				int bytesRead = blockChannel.read(buffer);
				int i = 0;
				while (bytesRead > 0) {
					buffer.flip();
					while (buffer.hasRemaining() && i < BLOCK_SIZE) {
						block[i++] = buffer.getInt();
					}
					buffer.clear();
					bytesRead = blockChannel.read(buffer);
				}
				Arrays.sort(block);
				blocks.add(block);
			}
		}

		// Merge sorted blocks using a PriorityQueue
		try (FileChannel outputChannel = new FileOutputStream(outputFile).getChannel()) {
			PriorityQueue<BlockIterator> queue = new PriorityQueue<BlockIterator>();
			for (int[] block : blocks) {
				queue.offer(new BlockIterator(block));
			}
			while (!queue.isEmpty()) {
				BlockIterator iterator = queue.poll();
				outputChannel.write((ByteBuffer) ByteBuffer.allocate(4).putInt(iterator.next()).flip());
				if (iterator.hasNext()) {
					queue.offer(iterator);
				}
			}
		}

		// Delete temporary block files
		for (File blockFile : blockFiles) {
			blockFile.delete();
		}
	}

	private static class BlockIterator implements Iterator<Integer>, Comparable<BlockIterator> {

		private int[] block;
		private int index = 0;

		public BlockIterator(int[] block) {
			this.block = block;
		}

		@Override
		public boolean hasNext() {
			return index < block.length;
		}

		@Override
		public Integer next() {
			return block[index++];
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

		@Override
		public int compareTo(BlockIterator other) {
			return Integer.compare(block[index], other.block[other.index]);
		}
	}
}
