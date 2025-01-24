package com.practice.collections.comparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * * This program demonstrates how to sort a list collection by multiple
 * attributes using a chained comparator. 
 */
public class SortingMultipleAttributesExample {

    public static void main(String[] args) {

        System.out.println("===== SORTING BY MULTIPLE ATTRIBUTES =====");
        List<Employee> listEmployees = new ArrayList<Employee>();

        listEmployees.add(new Employee("Tom", "Developer", 45, 80000));
        listEmployees.add(new Employee("Sam", "Designer", 30, 75000));
        listEmployees.add(new Employee("Bob", "Designer", 45, 134000));
        listEmployees.add(new Employee("Peter", "Programmer", 25, 60000));
        listEmployees.add(new Employee("Tim", "Designer", 45, 130000));
        listEmployees.add(new Employee("Craig", "Programmer", 30, 52000));
        listEmployees.add(new Employee("Anne", "Programmer", 25, 51000));
        listEmployees.add(new Employee("Alex", "Designer", 30, 120000));
        listEmployees.add(new Employee("Bill", "Programmer", 22, 30000));
        listEmployees.add(new Employee("Samuel", "Developer", 28, 80000));
        listEmployees.add(new Employee("John", "Developer", 35, 67000));
        listEmployees.add(new Employee("Patrick", "Developer", 35, 140000));
        listEmployees.add(new Employee("Alice", "Programmer", 35, 80000));
        listEmployees.add(new Employee("David", "Developer", 35, 99000));
        listEmployees.add(new Employee("Jane", "Designer", 30, 82000));

        System.out.println("*** Before sorting:");

        for (Employee emp : listEmployees) {
            System.out.println(emp);
        }

        System.out.println("\n*** After sorting 1:");

        Collections.sort(listEmployees);

        for (Employee emp : listEmployees) {
            System.out.println(emp);
        }

        Collections.sort(listEmployees, new EmployeeChainedComparator(new EmployeeJobTitleComparator(), new EmployeeAgeComparator(), new EmployeeSalaryComparator()));

        System.out.println("\n*** After sorting 2:");
        for (Employee emp : listEmployees) {
            System.out.println(emp);
        }

    }
}

class Employee implements Comparable<Employee> {
    private String name;
    private String jobTitle;
    private int age;
    private int salary;

    private Integer id;
    private Integer ctc;

    public Employee(String name, String jobTitle, int age, int salary) {
        this.name = name;
        this.jobTitle = jobTitle;
        this.age = age;
        this.salary = salary;
    }

    // getters and setters

    public int getAge() {
        return age;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String toString() {
        return String.format("%s\t%s\t%d\t%d", name, jobTitle, age, salary);
    }

    @Override
    public int compareTo(Employee record1) {
        int c;
        c = this.getJobTitle()
            .compareTo(record1.getJobTitle());
        if (c == 0)
            c = this.getAge() - record1.getAge();
        if (c == 0)
            c = this.getSalary() - record1.getSalary();
        return c;
    }
}

/**
 * This is a chained comparator that is used to sort a list by multiple
 * attributes by chaining a sequence of comparators of individual fields
 * together.
 * 
 * @author www.codejava.net
 *
 */
class EmployeeChainedComparator implements Comparator<Employee> {

    private List<Comparator<Employee>> listComparators;

    @SafeVarargs
    public EmployeeChainedComparator(Comparator<Employee>... comparators) {
        this.listComparators = Arrays.asList(comparators);
    }

    @Override
    public int compare(Employee emp1, Employee emp2) {
        for (Comparator<Employee> comparator : listComparators) {
            int result = comparator.compare(emp1, emp2);
            if (result != 0) {
                return result;
            }
        }
        return 0;
    }
}

/**
 * This comparator compares two employees by their job titles.
 * 
 * @author www.codejava.net
 *
 */
class EmployeeJobTitleComparator implements Comparator<Employee> {

    @Override
    public int compare(Employee emp1, Employee emp2) {
        return emp1.getJobTitle()
            .compareTo(emp2.getJobTitle());
    }
}

/**
 * This comparator compares two employees by their ages.
 * 
 * @author www.codejava.net
 *
 */
class EmployeeAgeComparator implements Comparator<Employee> {

    @Override
    public int compare(Employee emp1, Employee emp2) {
        return emp1.getAge() - emp2.getAge();
    }
}

/**
 * This comparator compares two employees by their salaries.
 * 
 * @author www.codejava.net
 *
 */
class EmployeeSalaryComparator implements Comparator<Employee> {

    @Override
    public int compare(Employee emp1, Employee emp2) {
        return emp1.getSalary() - emp2.getSalary();
    }
}