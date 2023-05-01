package com.practice.collections.comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Person {
    String firstName;
    String lastName;
    int age;
    String country;

    public Person(String firstName, String lastName, int age, String country) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.country = country;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getCountry() {
        return country;
    }

    public String toString() {
        return firstName + "|" + lastName + "|" + age + "|" + country;
    }
}

class PersonUtils {

    public static void main(String[] args) {
        Person p1 = new Person("Marlon", "Brando", 80, "USA");
        Person p2 = new Person("Bruce", "Lee", 32, "China");
        Person p3 = new Person("Brandon", "Lee", 28, "China");
        List<Person> person = new ArrayList<>();

        person.add(p1);
        person.add(p2);
        person.add(p3);
        String[] sortBy = { "firstname" };
        sort(person, sortBy);
    }

    public static void sort(List<Person> persons, String[] sortBy) {

        List<Comparator<Person>> listComparators = new ArrayList<>();
        for (int i = 0; i < sortBy.length; i++) {
            if (sortBy[i].equalsIgnoreCase("firstName"))
                listComparators.add(new PersonFirstNameComparator());
            if (sortBy[i] == "lastName")
                listComparators.add(new PersonLastNameComparator());
            if (sortBy[i] == "age")
                listComparators.add(new PersonAgeComparator());
            if (sortBy[i] == "country")
                listComparators.add(new PersonCountryComparator());
        }
        PersonChainedComparator pc = new PersonChainedComparator();
        pc.setListComparators(listComparators);
        Collections.sort(persons, pc);
        System.out.println("" + persons.size());
    }

}

/*static String smallestString(List<String> substrings) {
	String[] arr = new String[substrings.size()];
	arr = substrings.toArray(arr);
	sort(arr, arr.length);
}

static void sort(String a[], int n) {
	// sort the array
	for (int i = 0; i < n; i++) {
		for (int j = i + 1; j < n; j++) {
			if ((a[i] + a[j]).compareTo(a[j] + a[i]) > 0) {
				String s = a[i];
				a[i] = a[j];
				a[j] = s;
			}
		}
	}
}

static String lexsmallest(String a[], int n) {
	
	sort(a, n);
	// Concatenating sorted strings
	String str = "";
	for (int i = 0; i < n; i++)
		str += a[i];
	return str;
}*/

class PersonChainedComparator implements Comparator<Person> {

    private List<Comparator<Person>> listComparators;

    public void setListComparators(List<Comparator<Person>> listComparators) {
        this.listComparators = listComparators;
    }

    @Override
    public int compare(Person p1, Person p2) {
        for (Comparator<Person> comparator : listComparators) {
            int result = comparator.compare(p1, p2);
            if (result != 0) {
                return result;
            }
        }
        return 0;
    }
}

class PersonFirstNameComparator implements Comparator<Person> {

    public int compare(Person p1, Person p2) {
        return p1.getFirstName()
            .compareTo(p2.getFirstName());
    }
}

class PersonAgeComparator implements Comparator<Person> {

    public int compare(Person p1, Person p2) {
        return p1.getAge() - p2.getAge();
    }
}

class PersonLastNameComparator implements Comparator<Person> {

    public int compare(Person p1, Person p2) {
        return p1.getLastName()
            .compareTo(p2.getLastName());
    }
}

class PersonCountryComparator implements Comparator<Person> {

    public int compare(Person p1, Person p2) {
        return p1.getCountry()
            .compareTo(p2.getCountry());
    }
}