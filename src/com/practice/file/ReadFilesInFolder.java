package com.practice.file;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;

public class ReadFilesInFolder {
	public static void main(String[] args) {
		// Specify the folder path you want to read files from
		String folderPath = "D:\\GitHub-Projects\\apiexample-1\\src\\main\\java";
		//String folderPath = "D:\\GitHub-Projects\\apiexample-1\\test";
		try {
			// Use Java NIO to walk through all files in the folder
			Files.walkFileTree(Paths.get(folderPath), EnumSet.noneOf(FileVisitOption.class), Integer.MAX_VALUE,
					new SimpleFileVisitor<Path>() {
						@Override
						public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
							// Check if the file is a regular file (not a directory or symbolic link)
							if (Files.isRegularFile(file)) {
								// Read and print the content of the file
								String content = new String(Files.readAllBytes(file));
								//System.out.println("File: " + file);
								System.out.println("Content:\n" + content);
								//System.out.println("------------------------------------------");
							}
							return FileVisitResult.CONTINUE;
						}

						@Override
						public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
							// Handle file visit failure, if necessary
							return FileVisitResult.CONTINUE;
						}
					});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
