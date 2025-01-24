package com.practice.assignment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Program to find duplicate files in the directory
 * 
 * @author Harshal
 *
 */
public class FindDuplicateFiles {
	private static MessageDigest messageDigest;
	static {
		try {
			messageDigest = MessageDigest.getInstance("SHA-512");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("cannot initialize SHA-512 hash function", e);
		}
	}

	public static void findDuplicateFiles(Map<String, List<String>> filesList, File directory) {
		for (File dirChild : directory.listFiles()) {
			// Iterate all file sub directories recursively
			if (dirChild.isDirectory()) {
				findDuplicateFiles(filesList, dirChild);
			} else {
				try {
					// Read file as bytes
					FileInputStream fileInput = new FileInputStream(dirChild);
					byte fileData[] = new byte[(int) dirChild.length()];
					fileInput.read(fileData);
					fileInput.close();
					// Create unique hash for current file
					String uniqueFileHash = new BigInteger(1, messageDigest.digest(fileData)).toString(16);
					List<String> identicalList = filesList.get(uniqueFileHash);
					if (identicalList == null) {
						identicalList = new LinkedList<String>();
					}
					// Add path to list
					identicalList.add(dirChild.getAbsolutePath());
					// push updated list to Hash table
					filesList.put(uniqueFileHash, identicalList);
				} catch (IOException e) {
					throw new RuntimeException("cannot read file " + dirChild.getAbsolutePath(), e);
				}
			}
		}
	}

	public static void main(String[] args) {
		File file = null;
		try {
			file = new File(
					FindDuplicateFiles.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String path = file.getParent();
		File dir = new File(path);
		if (!dir.isDirectory()) {
			System.out.println("Supplied directory does not exist.");
		}
		Map<String, List<String>> lists = new HashMap<String, List<String>>();
		FindDuplicateFiles.findDuplicateFiles(lists, dir);
		for (List<String> list : lists.values()) {

			if (list.size() > 1) {
				for (String file1 : list) {
					System.out.println("Duplicate File " + file1);
				}
			}
		}

	}

}
