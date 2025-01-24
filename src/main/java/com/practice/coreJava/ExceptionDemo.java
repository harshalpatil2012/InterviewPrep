package com.practice.coreJava;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ExceptionDemo {

    public static void main(String[] args) {
        String str = null;
        if (str == null) {
            try {
                throw new CustomException("IND400");
            } catch (CustomException e) {
                System.out.println("msg::" + e.getMessage());
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        // System.out.println(read());

    }

    @SuppressWarnings("finally")
    private static String read() {
        Scanner scanner;
        StringBuffer pacsXml = null;
        try {
            scanner = new Scanner(new File("C:/Users/608300564/Desktop/Wbc Testing/ProgressVLAN_Req.xml"));

            pacsXml = new StringBuffer();

            while (scanner.hasNextLine()) {
                pacsXml.append(scanner.nextLine());
            }
            scanner.close();
            // System.out.println("pacsXml->" + pacsXml.toString());
            return "Returning from TRY";
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "Returning from EXP";
        } finally {
            return "Returning from finally";
        }
        // return pacsXml.toString();
    }
}

class CustomException extends Exception {

    /**
     * IdentityHashMap 
     */
    private static final long serialVersionUID = 1L;
    String msg;

    public CustomException(String message) {
        msg = message;
    }

    @Override
    public String getMessage() {
        // TODO Auto-generated method stub
        return this.msg;
    }
}