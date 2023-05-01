package com.practice.misc;
public class StringOps {

    public static void main(String[] args) {

        String str1 = "abc";
        String str2 = new String("abc");
        String str3 = new String("abc");
        if (str1.equals(str2)) {
            System.out.println("Strings 1 & 2 are equal as per equals method");
        }
        if (str1 == str2) {
            System.out.println("Strings  1 & 2 are equal as per == operator");
        }
        if (str3.equals(str2)) {
            System.out.println("Strings 2 & 3 are equal as per equals method");
        }
        if (str3 == str2) {
            System.out.println("Strings 2 & 3 are equal as per == operator");
        }
        str3.intern();
        str2.intern();
        if (str3.equals(str2)) {
            System.out.println("Strings intern 2 & 3 are equal as per equals method");
        }
        if (str3 == str2) {
            System.out.println("Strings intern 2 & 3 are equal as per == operator");
        }

        if (str1 == str2) {
            System.out.println("Strings intern 1 & 2 are equal as per == operator");
        }

        /*String str = "ab c (d(s)f(d)sf) dfds";
        char[] ch = str.toCharArray();
        int count=0;
        for (int i = 0; i < ch.length; i++) {
        	if(ch[i]=='(') {
        		System.out.println("Position of matching opening bracket");
        		count =1;
        	}
        	if(ch[i]==')') {
        		System.out.println("Position of matching closing bracket" + i);
        		count =1;
        	}
        		
        }*/
    }
}
