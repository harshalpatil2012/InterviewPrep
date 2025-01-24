package com.practice.collections.comparator;

import java.util.Comparator;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;

public class TreeSetDemo {

    public static void main(String[] args) {

        TreeSet<EmployeeCls> empTreeSet = new TreeSet<EmployeeCls>(new EmployeeClsComparator());

        EmployeeCls emp1 = new EmployeeCls(20, "Clark");
        EmployeeCls emp2 = new EmployeeCls(24, "Bernie");
        EmployeeCls emp3 = new EmployeeCls(3, "Alex");

        empTreeSet.add(emp1);
        empTreeSet.add(emp2);
        empTreeSet.add(emp3);

        for (EmployeeCls emp : empTreeSet)
            System.out.print(emp.name + " ");

        TreeMap<String, Integer> map = new TreeMap<>();
        map.put("C", 11);
        map.put("N", 34);
        map.put("J", 36);
        map.put("A", 4);
        for (Entry<String, Integer> m : map.entrySet()) {
            System.out.println("MAP key::" + m.getKey() + " value" + m.getValue());
        }

    }
}

class EmployeeCls implements Comparable<EmployeeCls> {

    int id;
    String name;

    EmployeeCls(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int compareTo(EmployeeCls emp) {
        return this.name.compareTo(emp.name);
    }
}

class EmployeeClsComparator implements Comparator<EmployeeCls> {

    @Override
    public int compare(EmployeeCls emp1, EmployeeCls emp2) {

        return emp2.id - emp1.id;
    }
}
