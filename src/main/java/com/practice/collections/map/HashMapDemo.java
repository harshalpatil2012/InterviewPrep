package com.practice.collections.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class HashMapDemo {

    public static void main(String[] args) {

        Set<String> set = new HashSet();
        set.add(null);
        Set<String> lset = new LinkedHashSet<>();
        set.add(null);
        /*
         * Set<String> tset = new TreeSet(); // Note:throws nullpointer
         * exception tset.add(null);
         */

        Student s1 = new Student();
        s1.setId(1);
        ;
        s1.setName("S1");
        Student s2 = new Student();
        s2.setId(2);
        ;
        s2.setName("S2");
        Student s3 = new Student();
        s3.setId(3);
        ;
        s3.setName("S3");
        System.out.println("S2 hashcode::" + s2.hashCode());
        System.out.println("S3 hashcode::" + s3.hashCode());
        HashMapDemo hmap = new HashMapDemo();
        Map<Student, String> studMap = new HashMap<Student, String>();
        // Map sorted map navigable map
        studMap.put(s1, "Student1");
        studMap.put(s2, "Student2");
        studMap.put(s3, "Student3");
        ArrayList<Student> list = new ArrayList<>();
        list.add(s1);
        list.add(s2);
        list.add(s3);
        Collections.sort((List<Student>) list);
        System.out.println("HasMap::" + studMap.size());
        s3.setName("S4");
        System.out.println("HasMap2::" + studMap.size());
        studMap = studMap;
        for (Entry<Student, String> entry : studMap.entrySet()) {
            System.out.println("Hascode for :: " + entry.getKey()
                .hashCode());
            if (s3.equals(entry.getKey())) {
                System.out.println("Got s3 equal");
            }

        }

        Map<Employee, String> empMap = new HashMap<Employee, String>();
        // Map sorted map navigable map
        empMap.put(new Employee("emp1"), "e1");
        empMap.put(new Employee("emp1"), "e2");
        Employee e1 = new Employee("emp1");

        System.out.println("emp value present::" + empMap.get(e1));

    }

}

class Student implements Comparable<Student> {

    int id;
    String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public boolean equals(Object obj) {
        if (this.name.equals(obj))
            return true;
        else
            return false;
    }

    public int compareTo(Student o) {
        // TODO Auto-generated method stub
        return 0;
    }

}

class Employee {
    String id;

    public Employee(String id) {
        this.id = id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
