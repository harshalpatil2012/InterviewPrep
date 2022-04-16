package codeInterView.assignment;

//Java program to find out maximum
//profit by buying and selling a
//share atmost k times given stock
//price of n days
import java.io.*;

public class TestApp {

    // Function to find out maximum profit by
    // buying & selling/ a share atmost k times
    // given stock price of n days
    static int maxProfit(int price[], int n, int k) {

        // table to store results of subproblems
        // profit[t][i] stores maximum profit
        // using atmost t transactions up to day
        // i (including day i)
        int profit[][] = new int[k + 1][n + 1];

        // For day 0, you can't earn money
        // irrespective of how many times you trade
        for (int i = 0; i <= k; i++)
            profit[i][0] = 0;

        // profit is 0 if we don't do any
        // transation (i.e. k =0)
        for (int j = 0; j <= n; j++)
            profit[0][j] = 0;

        // fill the table in bottom-up fashion
        for (int i = 1; i <= k; i++) {
            int prevDiff = Integer.MIN_VALUE;
            for (int j = 1; j < n; j++) {
                prevDiff = Math.max(prevDiff, profit[i - 1][j - 1] - price[j - 1]);
                profit[i][j] = Math.max(profit[i][j - 1], price[j] + prevDiff);
            }
        }

        return profit[k][n - 1];
    }

    // Driver code
    public static void main1(String[] args) {
        int k = 1;
        int price[] = { 12, 14, 17, 10, 14, 13, 12, 15 };

        int n = price.length;

        System.out.println("Maximum profit is: " + maxProfit(price, n, k));
    }
}
