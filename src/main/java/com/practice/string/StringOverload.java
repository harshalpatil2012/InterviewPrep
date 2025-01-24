package com.practice.string;

public class StringOverload {

	public static void main(String str[]) {
		print(null);
	}

	public static void print(String str) {
		System.out.println("Inside string ");
	}

	
	public static void print(Object obj) {
		System.out.println("Inside object ");

	}
}
