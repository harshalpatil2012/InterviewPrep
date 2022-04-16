import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.NotActiveException;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Stack;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class A1 {
    public int i;
    protected int j;
}

abstract class Abstract {

}

class B2 extends A1 {
    int j;

    void display() {
        super.j = 3;
        super.i = 10;
        System.out.println(i + " " + j);
    }
}

class Exc0 extends Exception {
}

class Exc1 extends Exc0 {
} /* Line 2 */

public class JavaCls {
    public static void main2(String args[]) {
        try {
            throw new Exc1(); /* Line 9 */
        } catch (Exc0 e0) /* Line 11 */
        {
            System.out.println("Ex0 caught");
        } catch (Exception e) {
            System.out.println("exception caught");
        }
    }

    static void characterCount(String inputString) {
        // Creating a HashMap containing char as a key and occurrences as a value

        LinkedHashMap<Character, Integer> charCountMap = new LinkedHashMap<Character, Integer>();

        // Converting given string to char array

        char[] strArray = inputString.toCharArray();

        // checking each char of strArray

        for (char c : strArray) {
            if (charCountMap.containsKey(c)) {
                charCountMap.put(c, charCountMap.get(c) + 1);
            } else {

                charCountMap.put(c, 1);
            }
        }

        StringBuilder strBuilder = new StringBuilder();
        for (Entry<Character, Integer> entry : charCountMap.entrySet()) {
            strBuilder.append(entry.getKey());
            strBuilder.append(entry.getValue());
        }

        System.err.println(strBuilder.toString());
    }

    static String token(String text) {
        String[] splits = text.split(" ");
        List<String> list = new ArrayList<>();
        String token = null;
        for (String s : splits) {

            if (s.startsWith("\"")) {
                token = "" + s;
            } else if (s.endsWith("\"")) {
                token = token + " " + s;
                list.add(token + "\n");
                token = null;
            } else {
                if (token != null) {
                    token = token + " " + s;
                } else {
                    list.add(s + "\n");
                }
            }
        }

        String str = list.toString();
        str = str.substring(1, str.length() - 1);

        return str;
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        System.err.println("list getClass:: " + list.getClass());

        System.err.println("list getSimpleName:: " + list.getClass()
            .getSimpleName());
        List<String> list1 = Collections.synchronizedList(list);

        String st[] = new String[10];
        // characterCount("Java J2EE Java JSP J2EE");

        String str = "Location \"Welcome  to india\" Bangalore " + "Channai \"IT city\"  Mysore";

        System.err.println(token(str));

    }
}

class JavaCls1 {

    public static void main(String args[]) {

        B2 obj = new B2();
        obj.i = 1;
        obj.j = 2;
        obj.display();
    }

    public static void main11(String args[]) throws Exception {
        Stack s = new Stack<>();
        s.push("A");
        s.push("B");
        System.out.println(s);
        System.out.println(s.search("Z"));
        System.out.println(s.isEmpty());
    }

    public void sampleMap() {
        TreeMap tm = new TreeMap();
        tm.put("a", "Hello");
        tm.put("b", "Java");
        tm.put("c", "World");
        Iterator it = tm.keySet()
            .iterator();
        while (it.hasNext()) {
            System.out.print(it.next());
        }
    }

    public static void main1(String args[]) throws Exception {
        JavaCls junk = new JavaCls();
        // junk.sampleMap();
    }
}
