package com.practice.thread.deadlock.deadlock._synchronized;

public class BankAccountDL {
    double balance;
    int id;

    BankAccountDL(int id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    void withdraw(double amount) {
        // Wait to simulate io like database access ...
        try {
            System.out.println("Inside withdraw");
            Thread.sleep(100l);

        } catch (InterruptedException e) {
        }
        balance -= amount;
    }

    void deposit(double amount) {
        // Wait to simulate io like database access ...
        try {
            System.out.println("Inside deposit");
            Thread.sleep(10l);
        } catch (InterruptedException e) {
        }
        balance += amount;
    }

    static void transfer(BankAccountDL from, BankAccountDL to, double amount) {
        synchronized (from) {
            from.withdraw(amount);
            System.out.println("After withdraw sync block");
            synchronized (to) {
                System.out.println("Inside deposit sync block");
                to.deposit(amount);
            }
        }
    }

    public static void main(String[] args) {
        final BankAccountDL fooAccount = new BankAccountDL(1, 100d);
        final BankAccountDL barAccount = new BankAccountDL(2, 100d);

        new Thread() {
            public void run() {
                BankAccountDL.transfer(fooAccount, barAccount, 10d);
            }
        }.start();

        new Thread() {
            public void run() {
                BankAccountDL.transfer(barAccount, fooAccount, 10d);
            }
        }.start();

    }
}
