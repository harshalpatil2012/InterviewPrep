package interviewProg;

public class Factorial {

    public static void main(String args[]) {
        int i, fact = 1;
        int number = 5;// It is the number to calculate factorial
        for (i = 1; i <= number; i++) {
            fact = fact * i;
        }
        System.out.println("Factorial of " + number + " is: " + fact);

        fact = factorial(number);
        System.out.println("Via rec. Factorial of " + number + " is: " + fact);
    }

    static int factorial(int n) {
        if (n == 0)
            return 1;
        else
            return (n * factorial(n - 1));
    }

}
