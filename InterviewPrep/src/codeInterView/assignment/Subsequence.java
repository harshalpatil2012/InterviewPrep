package codeInterView.assignment;

import java.util.Scanner;

class Codechef {
    public static void main(String[] args) throws java.lang.Exception {
        Scanner reader = new Scanner(System.in);
        int t = reader.nextInt();
        while (t-- > 0) {
            int n = reader.nextInt();
            int i;
            int[] seq = new int[n];
            for (i = 0; i < n; i++)
                seq[i] = reader.nextInt();
            int m = reader.nextInt();

            int[] subSeq = new int[m];
            for (i = 0; i < m; i++)
                subSeq[i] = reader.nextInt();
            if (isSubSequence(subSeq, seq)) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
    }

    public static boolean isSubSequence(int[] subSeq, int[] seq) {
        int i, j;
        for (i = 0, j = 0; i < subSeq.length && j < seq.length;) {
            if (subSeq[i] == seq[j])
                i++;
            j++;
        }
        return i == subSeq.length ? true : false;
    }
}