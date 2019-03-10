package codeInterView.thread.deadlock.livelock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccountLL {
    double balance;
    int id;
    Lock lock = new ReentrantLock();

    BankAccountLL(int id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    boolean withdraw(double amount) {
        if (this.lock.tryLock()) {
            // Wait to simulate io like database access ...
            try {
                Thread.sleep(10l);
            } catch (InterruptedException e) {
            }
            balance -= amount;
            return true;
        }
        return false;
    }

    boolean deposit(double amount) {
        if (this.lock.tryLock()) {
            // Wait to simulate io like database access ...
            try {
                Thread.sleep(10l);
            } catch (InterruptedException e) {
            }
            balance += amount;
            return true;
        }
        return false;
    }

    public boolean tryTransfer(BankAccountLL destinationAccount, double amount) {
        if (this.withdraw(amount)) {
            if (destinationAccount.deposit(amount)) {
                return true;
            } else {
                // destination account busy, refund source account.
                this.deposit(amount);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        final BankAccountLL fooAccount = new BankAccountLL(1, 500d);
        final BankAccountLL barAccount = new BankAccountLL(2, 500d);

        new Thread(new Transaction(fooAccount, barAccount, 10d), "transaction-1").start();
        new Thread(new Transaction(barAccount, fooAccount, 10d), "transaction-2").start();

    }

}

class Transaction implements Runnable {
    private BankAccountLL sourceAccount, destinationAccount;
    private double amount;

    Transaction(BankAccountLL sourceAccount, BankAccountLL destinationAccount, double amount) {
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.amount = amount;
    }

    public void run() {
        while (!sourceAccount.tryTransfer(destinationAccount, amount))
            continue;
        System.out.printf("%s completed ", Thread.currentThread()
            .getName());
    }

}