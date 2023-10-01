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

/**
 * Given two arrays of strings, determine whether corresponding elements contain
 * a common substring.
 * 
 * Example a = ['ab','cd','ef'] b = ['af', 'ee', 'ef'] Make the following
 * decisions: i a[i] b[i] Common Result 0 ab af a YES 1 cd ee NO 2 ef ef ef YES
 * 
 * For each test, print the result on a new line, either YES if there is a
 * common substring, or No
 * 
 * Function Description Complete the function commonSubstring in the editor
 * below. For each a[i], b[i] pair, the function must print YES if they share a
 * common substring, or NO on a new line.
 * 
 * commonSubstring has the following parameter(s): string a[n]: an array of
 * strings string b[n]: an array of strings Return void: output should be
 * printed to stdout (console.log() in javascript) rather than returned
 * 
 * Constraints All the strings consist of lowercase English letters only,
 * ascii[a-z]
 * 
 * |a| = |b|
 * 
 * 1 ≤ |a|, |b| ≤ 10 1 ≤ |a[i]|, |b[i]| ≤ 10
 * 
 * Sample Input 1 STDIN Function ----- -------- 2 → a[] size n = 2 hello → a =
 * ['hello', 'hi'] hi 2 → b[] size n = 2 world → b = ['world', 'bye'] bye
 * 
 * Sample Output 1 YES NO
 * 
 * @author harshal
 *
 */
class Result {

	/*
	 * Complete the 'commonSubstring' function below.
	 *
	 * The function accepts following parameters: 1. STRING_ARRAY a 2. STRING_ARRAY
	 * b
	 */

	public static void commonSubstring(List<String> a, List<String> b) {
		// Iterate over each pair of strings in the ArrayLists
		for (int i = 0; i < a.size(); i++) {
			String strA = a.get(i);
			String strB = b.get(i);

			// Create a set of characters from strA
			HashSet<Character> charSet = new HashSet<>();
			for (char c : strA.toCharArray()) {
				charSet.add(c);
			}

			// Check if any character in strB exists in the set
			boolean hasCommonSubstring = false;
			for (char c : strB.toCharArray()) {
				if (charSet.contains(c)) {
					hasCommonSubstring = true;
					break;
				}
			}

			// Print the result
			if (hasCommonSubstring) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}
	}

}

public class CommonSubString {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		int aCount = Integer.parseInt(bufferedReader.readLine().trim());

		List<String> a = IntStream.range(0, aCount).mapToObj(i -> {
			try {
				return bufferedReader.readLine();
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		}).collect(toList());

		int bCount = Integer.parseInt(bufferedReader.readLine().trim());

		List<String> b = IntStream.range(0, bCount).mapToObj(i -> {
			try {
				return bufferedReader.readLine();
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		}).collect(toList());

		Result.commonSubstring(a, b);

		bufferedReader.close();
	}
}
