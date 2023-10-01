package com.practice.desPattern.factory;

//Account.java
interface Account {
	void deposit(double amount);

	void withdraw(double amount);

	String getAccountNumber();

	double getBalance();
}

//SavingsAccount.java
class SavingsAccount implements Account {
	private String accountNumber;
	private String accountHolder;
	private double balance;
	private double interestRate = 0.02; // 2% interest rate

	public SavingsAccount(String accountNumber, String accountHolder) {
		this.accountNumber = accountNumber;
		this.accountHolder = accountHolder;
	}

	public void deposit(double amount) {
		balance += amount;
	}

	public void withdraw(double amount) {
		if (balance >= amount) {
			balance -= amount;
		} else {
			System.out.println("Insufficient balance.");
		}
	}

	@Override
	public String getAccountNumber() {
		return this.accountHolder;
	}

	@Override
	public double getBalance() {
		return this.balance;
	}
}

//CheckingAccount.java
class CheckingAccount implements Account {
	private String accountNumber;
	private String accountHolder;
	private double balance;
	private double overdraftLimit = 1000; // $1000 overdraft limit

	public CheckingAccount(String accountNumber, String accountHolder) {
		this.accountNumber = accountNumber;
		this.accountHolder = accountHolder;
	}

	public void deposit(double amount) {
		balance += amount;
	}

	public void withdraw(double amount) {
		if (balance + overdraftLimit >= amount) {
			balance -= amount;
		} else {
			System.out.println("Insufficient balance.");
		}
	}

	@Override
	public String getAccountNumber() {
		return this.accountHolder;
	}

	@Override
	public double getBalance() {
		return this.balance;
	}
}

// Nomal factory design pattern
class AccountFactory {
	public Account createAccount(String accountType, String accountNumber, String accountHolder) {
		if ("savings".equals(accountType)) {
			return new SavingsAccount(accountNumber, accountHolder);
		} else if ("checking".equals(accountType)) {
			return new CheckingAccount(accountNumber, accountHolder);
		} else {
			throw new IllegalArgumentException("Invalid account type.");
		}
	}
}

//Transaction.java
interface Transaction {
	void process();
}

//DepositTransaction.java
class DepositTransaction implements Transaction {
	private Account account;
	private double amount;

	public DepositTransaction(Account account, double amount) {
		this.account = account;
		this.amount = amount;
	}

	public void process() {
		account.deposit(amount);
		System.out.println("Deposited " + amount + " into account " + account.getAccountNumber() + ".");
	}
}

//WithdrawTransaction.java
class WithdrawTransaction implements Transaction {
	private Account account;
	private double amount;

	public WithdrawTransaction(Account account, double amount) {
		this.account = account;
		this.amount = amount;
	}

	public void process() {
		account.withdraw(amount);
		System.out.println("Withdrew " + amount + " from account " + account.getAccountNumber() + ".");
	}
}

//TransactionFactory.java
class TransactionFactory {
	public Transaction createDepositTransaction(Account account, double amount) {
		return new DepositTransaction(account, amount);
	}

	public Transaction createWithdrawTransaction(Account account, double amount) {
		return new WithdrawTransaction(account, amount);
	}
}

//AbstractFactory design patterns
public class AbstractFactory {
	public static void main(String[] args) {
		AccountFactory accountFactory = new AccountFactory();
		TransactionFactory transactionFactory = new TransactionFactory();

		// Create a savings account
		Account savingsAccount = accountFactory.createAccount("savings", "SAV-123", "John Doe");
		savingsAccount.deposit(500);

		// Create a deposit transaction for the savings account
		Transaction savingsDepositTransaction = transactionFactory.createDepositTransaction(savingsAccount, 200);
		savingsDepositTransaction.process();
		System.out.println(savingsAccount.getBalance()); // Output: 700

		// Create a checking account
		Account checkingAccount = accountFactory.createAccount("checking", "CHK-456", "Jane Smith");
		checkingAccount.deposit(1000);

		// Create a withdraw transaction for the checking account
		Transaction checkingWithdrawTransaction = transactionFactory.createWithdrawTransaction(checkingAccount, 500);
		checkingWithdrawTransaction.process();
		System.out.println(checkingAccount.getBalance()); // Output: 500
	}
}
