package com.practice.assignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;

/**
 * Given a file input.txt . Our Task is to remove duplicate lines from it and save the output in file say output.txt
 * @author harshal
 *
 */
public class DeleteDupLinesFromFile {

    public static void main(String[] args) throws IOException {
        // PrintWriter object for output.txt
        PrintWriter pw = new PrintWriter("output.txt");
        // BufferedReader object for input.txt
        BufferedReader br1 = new BufferedReader(new FileReader("input.txt"));
        String line1 = br1.readLine();
        // loop for each line of input.txt
        while (line1 != null) {
            boolean flag = false;

            // BufferedReader object for output.txt
            BufferedReader br2 = new BufferedReader(new FileReader("output.txt"));
            String line2 = br2.readLine();
            // loop for each line of output.txt
            while (line2 != null) {
                if (line1.equals(line2)) {
                    flag = true;
                    break;
                }
                line2 = br2.readLine();
            }
            // if flag = false
            // write line of input.txt to output.txt
            if (!flag) {
                pw.println(line1);
                // flushing is important here
                pw.flush();
            }
            line1 = br1.readLine();
            br2.close();

        }
        // closing resources
        br1.close();
        pw.close();
        System.out.println("File operation performed successfully");
    }

    // Using hashset
    public static void main1(String[] args) throws IOException {
        // PrintWriter object for output.txt
        PrintWriter pw = new PrintWriter("output.txt");

        // BufferedReader object for input.txt
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        String line = br.readLine();
        // set store unique values
        HashSet<String> hs = new HashSet<String>();
        // loop for each line of input.txt
        while (line != null) {
            // write only if not present in hashset
            if (hs.add(line))
                pw.println(line);
            line = br.readLine();
        }
        pw.flush();

        // closing resources
        br.close();
        pw.close();
        System.out.println("File operation performed successfully");
    }
}
