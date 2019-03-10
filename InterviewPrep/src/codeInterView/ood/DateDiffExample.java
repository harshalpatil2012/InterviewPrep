package codeInterView.ood;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.joda.time.Days;
import org.joda.time.LocalDate;

public class DateDiffExample {
    private static final DateFormat df = new SimpleDateFormat("yyyy/MM/dd");

    public static void main(String args[]) throws ParseException {
        System.out.println("Please enter two dates in format yyyy/MM/dd to compare");
        Scanner reader = new Scanner(System.in);
        String first = reader.nextLine();
        String second = reader.nextLine();
        Date one = getDate(first);
        Date two = getDate(second);
        // quick and dirty way, work but not in all conditions // you can
        // convert date into milliseconds then subtract
        // them and again convert it to days
        long numberOfDays = daysBetween(one, two);
        System.out.printf("Number of days between date %s and %s is : %d %n", first, second, numberOfDays);
        // a better way to calculate difference between two dates in Java // is
        // by using JodaTime library as shown below
        int differenceBetweenDates = daysBetweenUsingJoda(one, two);
        System.out.printf("difference betweeen two dates %s and %s is : %d %n", first, second, differenceBetweenDates);
        reader.close();
    }

    /* * Simple way to parse String to date in Java */ private static Date getDate(String date) throws ParseException {
        return df.parse(date);
    }

    /*
     * * Java Method to calculate difference between two dates in Java * without
     * using any third party library.
     */
    private static long daysBetween(Date one, Date two) {
        long difference = (one.getTime() - two.getTime()) / 86400000;
        return Math.abs(difference);
    }

    /*
     * * Java Method to find number of days between two dates * in Java using
     * JodaTime library. To find difference * we first need to convert
     * java.util.Date to LocalDate * in JodaTime.
     */
    public static int daysBetweenUsingJoda(Date d1, Date d2) {
        return Days.daysBetween(new LocalDate(d1.getTime()), new LocalDate(d2.getTime()))
            .getDays();
    }
}
