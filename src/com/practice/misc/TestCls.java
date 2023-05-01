
package com.practice.misc;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class TestCls implements LotsOfColors {

	private static String s;

	public static void main2(String[] args) throws InterruptedException {
		print();
		/*
		 * System.out.println("" + YELLOW); new newthread(); Thread.sleep(2000); int
		 * arr[] = new int[5]; System.out.print(arr); System.out.println(Season.SUMMER);
		 * // int dist = getLevenshteinDistance( "5111", "752961348");
		 * 
		 * // System.out.println("DIst::" + dist);
		 * 
		 * Object[] names = new String[3]; // names[0] = new Integer(0);
		 */ }

	static void print() {
		System.out.println("INSIDE static");
	}

	public static void main111(String[] args) {

		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		/*
		 * list.add(4); list.add(5); list.add(6);
		 */
		System.out.println(sum(list));
		for (Integer integer : list) {
			// list.remove(0);
			System.out.println("integer::" + integer);
		}
		Iterator iter = list.iterator();
		while (iter.hasNext()) {

			System.out.println("integer::" + iter.next());
			list.remove(0);
		}

	}

	public static double sum(List<? extends Number> list) {
		// list.add(new Integer(4);
		double sum = 0;
		for (Number num : list) {
			sum += num.doubleValue();
		}
		return sum;
	}

	enum Season {
		WINTER, SPRING, SUMMER, FALL;

		Season() {
			System.out.println("Inside cont");
		}
	};

	// Misc
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * Find the Levenshtein distance between two Strings.
	 * </p>
	 * <p>
	 * This is the number of changes needed to change one String into another, where
	 * each change is a single character modification (deletion, insertion or
	 * substitution).
	 * </p>
	 * <p>
	 * The previous implementation of the Levenshtein distance algorithm was from
	 * <a href="http://www.merriampark.com/ld.htm">http://www.merriampark.com/ld
	 * .htm</a>
	 * </p>
	 * <p>
	 * Chas Emerick has written an implementation in Java, which avoids an
	 * OutOfMemoryError which can occur when my Java implementation is used with
	 * very large strings.<br>
	 * This implementation of the Levenshtein distance algorithm is from
	 * <a href="http://www.merriampark.com/ldjava.htm">http://www.merriampark.
	 * com/ldjava.htm</a>
	 * </p>
	 *
	 * <pre>
	 * StringUtils.getLevenshteinDistance(null, *)             = IllegalArgumentException
	 * StringUtils.getLevenshteinDistance(*, null)             = IllegalArgumentException
	 * StringUtils.getLevenshteinDistance("","")               = 0
	 * StringUtils.getLevenshteinDistance("","a")              = 1
	 * StringUtils.getLevenshteinDistance("aaapppp", "")       = 7
	 * StringUtils.getLevenshteinDistance("frog", "fog")       = 1
	 * StringUtils.getLevenshteinDistance("fly", "ant")        = 3
	 * StringUtils.getLevenshteinDistance("elephant", "hippo") = 7
	 * StringUtils.getLevenshteinDistance("hippo", "elephant") = 7
	 * StringUtils.getLevenshteinDistance("hippo", "zzzzzzzz") = 8
	 * StringUtils.getLevenshteinDistance("hello", "hallo")    = 1
	 * </pre>
	 *
	 * @param s
	 *            the first String, must not be null
	 * @param t
	 *            the second String, must not be null
	 * @return result distance
	 * @throws IllegalArgumentException
	 *             if either String input <code>null</code>
	 */
	public static int getLevenshteinDistance(String s, String t) {
		if (s == null || t == null) {
			throw new IllegalArgumentException("Strings must not be null");
		}

		/*
		 * The difference between this impl. and the previous is that, rather than
		 * creating and retaining a matrix of size s.length()+1 by t.length()+1, we
		 * maintain two single-dimensional arrays of length s.length()+1. The first, d,
		 * is the 'current working' distance array that maintains the newest distance
		 * cost counts as we iterate through the characters of String s. Each time we
		 * increment the index of String t we are comparing, d is copied to p, the
		 * second int[]. Doing so allows us to retain the previous cost counts as
		 * required by the algorithm (taking the minimum of the cost count to the left,
		 * up one, and diagonally up and to the left of the current cost count being
		 * calculated). (Note that the arrays aren't really copied anymore, just
		 * switched...this is clearly much better than cloning an array or doing a
		 * System.arraycopy() each time through the outer loop.)
		 * 
		 * Effectively, the difference between the two implementations is this one does
		 * not cause an out of memory condition when calculating the LD over two very
		 * large strings.
		 */

		int n = s.length(); // length of s
		int m = t.length(); // length of t

		if (n == 0) {
			return m;
		} else if (m == 0) {
			return n;
		}

		if (n > m) {
			// swap the input strings to consume less memory
			String tmp = s;
			s = t;
			t = tmp;
			n = m;
			m = t.length();
		}

		int p[] = new int[n + 1]; // 'previous' cost array, horizontally
		int d[] = new int[n + 1]; // cost array, horizontally
		int _d[]; // placeholder to assist in swapping p and d

		// indexes into strings s and t
		int i; // iterates through s
		int j; // iterates through t

		char t_j; // jth character of t

		int cost; // cost

		for (i = 0; i <= n; i++) {
			p[i] = i;
		}

		for (j = 1; j <= m; j++) {
			t_j = t.charAt(j - 1);
			d[0] = j;

			for (i = 1; i <= n; i++) {
				cost = s.charAt(i - 1) == t_j ? 0 : 1;
				// minimum of cell to the left+1, to the top+1, diagonally left
				// and up +cost
				d[i] = Math.min(Math.min(d[i - 1] + 1, p[i] + 1), p[i - 1] + cost);
			}

			// copy current distance counts to 'previous row' distance counts
			_d = p;
			p = d;
			d = _d;
		}

		// our last action in the above loop was to switch d and p, so p now
		// actually has the most recent cost counts
		return p[n];
	}

	public static java.util.Date addNPlusDaysToDate(Date date, int ndays) {
		s = "";
		if (3 * 0.1 == 0.3) {
			System.out.println("Inside IF true");
		} else {
			System.out.println("Inside  else");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = java.util.Calendar.getInstance();
		c.setTime(date); // Now use today date
		c.add(Calendar.DATE, ndays); // Adding n days
		List<String> list = new ArrayList<String>();
		// list.add(index)
		return c.getTime();

	}

	public enum Dogs {
		collie, harrier, shepherd
	}

	public static void main21(String[] args) {
		Dogs myDog = Dogs.shepherd;
		String str = "";
		switch (myDog) {
		default:
			System.out.print("retriever ");
			break;
		case collie:
			System.out.print("collie ");

		case harrier:
			System.out.print("harrier ");
		}
	}

	public void go() {
		Runnable r = new Runnable() {
			public void run() {
				System.out.print("foo");

			}
		};
		Thread t = new Thread(r);
		t.start();
		t.start();
	}

	/*
	 * public static void main122(String...a) {
	 * 
	 * } public static void main122(String.* a) {
	 * 
	 * } public static void main1221(String... a) {
	 * 
	 * } public static void main122(String[]... a) { } public static void
	 * main1221(String...[] a) {
	 * 
	 * }
	 */
	public static void main(String[] args) throws InterruptedException {
		new TestCls().go();
		// new TestCls().waitForSignal();
		thread Thread = new thread();
		Thread.run();

		Session ses = new ConT();
		ses.display();
		Random r = new Random();
		int lower = 30;
		int upper = 100;
		int Result = (int) (Math.random() * (upper - lower)) + lower;
		System.out.println("Delay::" + Result);
	}

	public static Date parseDateString(final String dateString, final String format) throws ParseException {

		DateFormat dateDf = new SimpleDateFormat(format);

		return dateDf.parse(dateString);
	}

	void waitForSignal() throws InterruptedException {
		Object obj = new Object();
		synchronized (this) {
			System.out.println("waitForSignal");
			obj.wait();
			obj.notify();
		}
	}

}

class newthread extends Thread {
	Thread t;

	newthread() {
		t = new Thread(this, "My Thread");
		t.start();
	}

	public void run() {
		try {
			t.join();
			// t.get
			System.out.println(t.getName());
		} catch (Exception e) {
			System.out.print("Exception");
		}
	}
}

class thread extends Thread {
	public synchronized final void run() {
		System.out.println("Inside run sync");
	}

}

class Plant {
	private String name;

	public Plant() {

	}

	public Plant(String name) {

		this.name = name;
	}

	public String getName() {
		return name;
	}
}

abstract class Session {
	abstract void display();
}

class ConT extends Session {
	void display() {
		System.out.println("Inside clonable");
	}

}

interface BaseColors {
	int RED = 1, GREEN = 2, BLUE = 4;
}

interface RainbowColors extends BaseColors {
	int YELLOW = 3, ORANGE = 5, INDIGO = 6, VIOLET = 7;
}

interface PrintColors extends BaseColors {
	int YELLOW = 8, CYAN = 16, MAGENTA = 32;
}

interface LotsOfColors extends RainbowColors, PrintColors {
	int FUCHSIA = 17, VERMILION = 43, CHARTREUSE = RED + 90, YELLOW = 1;
}

class Tree extends Plant {
	/*
	 * public Tree(String name) { super(name); // TODO Auto-generated constructor
	 * stub }
	 */

	public void growFruit() {
	}

	public void dropLeaves() {
	}
}