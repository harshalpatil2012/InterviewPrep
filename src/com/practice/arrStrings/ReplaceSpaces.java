package com.practice.arrStrings;
/* URLify a given string (Replace spaces with %20). Write a method to replace all spaces in a string with '%20',
 *  you may assume   that the string has sufficient space at the end of the string to hold the additional characters, 
 *  and that you are given the "true" length of the string.
 * (Note: if implementing in Java, please use a character array so that you can perform this operation in place)
*/

//edit the string starting from the end and work backwards(have extra buffer at the end)
//two scan approach
//first:count how many spaces there are in the string
//second:editing the string
public class ReplaceSpaces {
	static int MAX = 1000;

	public static void main(String[] args) {

		String strReplace = "My Work space";
		System.out.println("replacesSpaces output::" + replaceSpaces(strReplace.toCharArray()));
	}

	static String replaceSpaces(char[] str) {

		// count spaces and find current length
		int space_count = 0, i = 0;
		for (i = 0; i < str.length; i++)
			if (str[i] == ' ')
				space_count++;

		// count spaces and find current length
		while (str[i - 1] == ' ') {
			space_count--;
			i--;
		}

		// Find new length.
		int new_length = i + space_count * 2;

		// New length must be smaller than length
		// of string provided.
		if (new_length > MAX)
			return new String(str);

		// Start filling character from end
		int index = new_length - 1;

		char[] old_str = str;
		str = new char[new_length];

		// Fill rest of the string from end
		for (int j = i - 1; j >= 0; j--) {

			// inserts %20 in place of space
			if (old_str[j] == ' ') {
				str[index] = '0';
				str[index - 1] = '2';
				str[index - 2] = '%';
				index = index - 3;
			}

			else {
				str[index] = old_str[j];
				index--;
			}
		}
		return new String(str);
	}
}