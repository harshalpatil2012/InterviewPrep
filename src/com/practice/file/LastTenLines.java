package com.practice.file;

import java.io.*;

/** 
 * Problem: Java program to find the last 10 lines in a file in an optimized way:
 * In this program, we use a RandomAccessFile to read the file backwards from the end. 
 * We start reading from the end of the file and keep moving backwards until we have found 10 lines. 
 * We count the number of lines by counting the number of \n characters we encounter. Once we have found 10 lines, 
 * we break out of the loop and reverse the order of the characters in the StringBuilder before printing them to 
 * the console. This ensures that the lines are printed in the correct order.
 * 
 */

/**
 * @author harshal
 *
 */

public class LastTenLines {
	public static void main(String[] args) {
		try {
			File file = new File("input.txt");
			RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
			long fileLength = file.length();
			long pointerPosition = fileLength - 1;
			int linesCount = 0;
			StringBuilder lastTenLinesBuilder = new StringBuilder();
			while (pointerPosition >= 0) {
				randomAccessFile.seek(pointerPosition);
				int currentByte = randomAccessFile.readByte();
				if (currentByte == '\n') {
					linesCount++;
					if (linesCount == 10) {
						break;
					}
				}
				lastTenLinesBuilder.append((char) currentByte);
				pointerPosition--;
			}
			lastTenLinesBuilder.reverse();
			System.out.println(lastTenLinesBuilder.toString());
			randomAccessFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
