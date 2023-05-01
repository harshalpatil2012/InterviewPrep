package com.practice.misc;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class TestApp extends Rock {
    /**
    	 * 
    	 */
    private static final long serialVersionUID = 1L;
    transient int n;
    static int b;

    public TestApp() {
        // super("Granite");
        new Rock("ROCK");
    }

    public static void main(String args[]) {
        iterateConHashMap();
        /*
         * new TestApp(); // getValue(); getCount(); countWord();
         */
        Object[] myobjects = { new Integer(12), new String("abc"), new Integer(2)/* , new Boolean(true) */ };
        /*
         * Arrays.sort(myobjects); String str="Hello"; for (int i = 0; i <
         * 1000000; i++) { str=str+" i"; System.out.println("String "+str); }
         * System.out.println("String final "+str);
         */

        List<String> list = new ArrayList<>();
        String str = null;
        List<String> listStr = list;
        listStr.add(str);
        List<String> listStr2 = listStr;
        System.out.println("list::" + list.get(0));
        System.out.println("listStr2::" + listStr2.get(0));
        str = "init";
        System.out.println("listStr::" + listStr.get(0));
        list.set(0, str);
        System.out.println("listStr::" + listStr.get(0));
        System.out.println("listStr2::" + listStr2.get(0));
        list = null;
        System.out.println("after null listStr::" + listStr.get(0));
        System.out.println("after null  listStr2::" + listStr2.get(0));

        String a = "test";
        String b1 = a;

        a = "wuut";

        System.out.println(b);
        Test2 t2 = new Test2();
        Test2 t3 = t2;
        System.out.println("Test2 t2 obj value::" + t2.value);
        t2.value = 100;

        System.out.println("Test2 t2 obj value::" + t2.value);
        System.out.println("Test2 t3 obj value::" + t3.value);

        ArrayList<String> list1 = new ArrayList<String>();
        ArrayList<String> list2 = new ArrayList<String>();
        list2.add("String");
        ArrayList list3 = new ArrayList();
        list3.add(new Integer(1));
        print1(list2);
        print1(list3);

        Object o1 = get0(new LinkedList<>());
        /*
         * Object o2= get0(new LinkedList<?>()); String s3= get0(new
         * LinkedList<String>());
         */
        Object o4 = get0(new LinkedList<Object>());
        // String s5= (String) get0(new LinkedList<String>());

        alpha();
        System.out.println("Static b ::" + b);
        parse("invalid");
    }

    static void alpha() { /* more code here */

        // beta();
        b = 10;
    }

    void beta() {
        b = 11;
        alpha();
    }

    public static Object get0(List list) {
        return list;
        // return list.get(0);
    }

    public static void parse(String str) {
        float f = 0;
        try {
            f = Float.parseFloat(str);
        } catch (NumberFormatException nfe) {
            f = 0;
        } finally {
            System.out.println("Parsed:: " + f);
        }
    }

    static void iterateConHashMap() {
        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("Str1", "1");
        concurrentHashMap.put("Str2", "2");
        concurrentHashMap.put("Str3", "3");
        concurrentHashMap.put("Str4", "4");

        for (Entry<String, String> entry : concurrentHashMap.entrySet()) {
            concurrentHashMap.put("Str6", "6");
            concurrentHashMap.put("Str7", "7");
            /*
             * concurrentHashMap.put("Str3339", "3886");
             * concurrentHashMap.put("Str3338", "3888");
             * concurrentHashMap.put("Str3336", "3889");
             * concurrentHashMap.put("Str33334", "388888");
             */
            System.out.println("HashMap value::" + entry.getValue());
        }

        /*
         * for (Entry<String, String> entry : concurrentHashMap.entrySet()) {
         * 
         * System.out.println("NEw Iternation HashMap value::" +
         * entry.getValue()); }
         */

    }

    public static void print1(List<String> list) {
        // String str = list.get(0);
        System.out.println(" list size::" + list.size());

    }

    public static int getValue() {
        return true ? null : 1;
    }

    public void print() {
        final String str = "";
        int n;
    }

    static void getCount() {
        String s = "2485083572085748";
        int sum = 0;
        for (int i = 0, len = s.length(); i < len; i++) {
            if (Character.isDigit(s.charAt(i))) {
                sum = sum + java.lang.Character.getNumericValue(s.charAt(i));
            }
        }
        System.out.println("Count::" + sum);
    }

    static void countWord() {
        Map<String, Integer> m = new HashMap<String, Integer>();
        String phrase = "hello my name is John I repeat John";
        String[] array = phrase.split(" ");

        for (int i = 0; i < array.length; i++) {
            String word_i = array[i];
            Integer ci = m.get(word_i);
            if (ci == null) {
                m.put(word_i, 1);
            } else
                m.put(word_i, ci + 1);
        }
        System.out.println("Count::" + m.size());
        System.out.println("hashCOde::" + new Test2().hashCode());
    }

}

class Test1 {
    public int value;

    public int hashCode() {
        return 42;
    }
}

class Test2 {
    public int value;

    public int hashCode() {
        return (int) (4 ^ 5);
    }
}

class Atom {
    Atom() {
        System.out.println("Imside Atom");
    }
}

class Rock extends Atom {
    public Rock() {
        // TODO Auto-generated constructor stub
    }

    Rock(String type) {
        System.out.println("Imside ROck" + type);
    }
}

class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public boolean equals(Person p) {
        return p.name.equals(this.name);
    }
}