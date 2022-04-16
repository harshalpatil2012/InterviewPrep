package codeInterView.coreJava;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class EqualsMethod {

    int equalCount = 0, hascodeCount = 0;

    public static void main(String[] args) {

        Object obj = new Object();
        print(obj);
        Object obj1 = new Integer("100");
        print(obj1);
        Object obj2 = new Exception();
        print(obj2);

        Integer int1 = -128;
        Integer int2 = -128;

        System.out.println(int1 == int2);

        EqualsMethod eq = new EqualsMethod();
        EqualsMethod.NewKeyClass key1 = eq.new NewKeyClass("honeywell");
        EqualsMethod.NewKeyClass key2 = eq.new NewKeyClass("Niagara");
        EqualsMethod.NewKeyClass key3 = eq.new NewKeyClass("phoenix");
        EqualsMethod.NewKeyClass key4 = eq.new NewKeyClass("novar");
        EqualsMethod.NewKeyClass key5 = eq.new NewKeyClass("alerton");

        Map<NewKeyClass, Object> map = new HashMap<NewKeyClass, Object>();
        Object valueObj = new Object();
        map.put(key1, valueObj);
        map.put(key2, valueObj);
        map.put(key3, valueObj);
        map.put(key4, valueObj);
        map.put(key5, valueObj);
        Iterator iter = map.keySet()
            .iterator();
        while (iter.hasNext()) {
            System.out.println("Next " + ((NewKeyClass) iter.next()).toString());
        }
    }

    public static void print(Object obj) {
        System.out.println("InsideObject print:: " + obj);
    }

    public static void print(Exception obj) {
        System.out.println("Inside Exception print:: " + obj);
    }

    public static void print(Integer obj) {
        System.out.println("Inside Integer print:: " + obj);
    }

    class NewKeyClass {

        private String keyName;

        public NewKeyClass(String keyName) {
            this.keyName = keyName;
        }

        public int hashCode() {
            hascodeCount++;
            System.out.println("equal hascodeCount::" + hascodeCount + " index::" + keyName.indexOf("a") + " keyName::" + keyName);
            if (keyName.indexOf("a") > -1) {
                return 5;
            } else {
                return keyName.length();
            }
        }

        public boolean equals(Object obj) {
            equalCount++;
            System.out.println("equal equalCount::" + equalCount + " index::" + keyName.indexOf("a") + " keyName::" + keyName);

            if (keyName.indexOf("a") > -1) {
                return true;
            } else {
                return false;
            }
        }

        public String toString() {
            return keyName;
        }
    }
}