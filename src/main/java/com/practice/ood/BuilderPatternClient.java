package com.practice.ood;

// BankAccount class (Product)
class BankAccount {
	private String accountNumber;
	private String accountHolderName;
	private String accountType;
	private double balance;
	private boolean isActive;

	// Private constructor, only accessed by the BankAccountBuilder
	private BankAccount() {
	}

	// Getters (no setters for immutability, attributes are set via builder)
	public String getAccountNumber() {
		return accountNumber;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public String getAccountType() {
		return accountType;
	}

	public double getBalance() {
		return balance;
	}

	public boolean isActive() {
		return isActive;
	}

	@Override
	public String toString() {
		return "BankAccount{" + "accountNumber='" + accountNumber + '\'' + ", accountHolderName='" + accountHolderName
				+ '\'' + ", accountType='" + accountType + '\'' + ", balance=" + balance + ", isActive=" + isActive
				+ '}';
	}

	// BankAccountBuilder (Builder)
	static class BankAccountBuilder {
		private BankAccount bankAccount;

		BankAccountBuilder() {
			bankAccount = new BankAccount();
		}

		BankAccountBuilder setAccountNumber(String accountNumber) {
			bankAccount.accountNumber = accountNumber;
			return this;
		}

		BankAccountBuilder setAccountHolderName(String accountHolderName) {
			bankAccount.accountHolderName = accountHolderName;
			return this;
		}

		BankAccountBuilder setAccountType(String accountType) {
			bankAccount.accountType = accountType;
			return this;
		}

		BankAccountBuilder setBalance(double balance) {
			bankAccount.balance = balance;
			return this;
		}

		BankAccountBuilder setActive(boolean isActive) {
			bankAccount.isActive = isActive;
			return this;
		}

		BankAccount build() {
			return bankAccount;
		}
	}
}

// Client code
public class BuilderPatternClient {
	public static void main(String[] args) {
		BankAccount bankAccount1 = new BankAccount.BankAccountBuilder().setAccountNumber("123456789")
				.setAccountHolderName("John Doe").setAccountType("Savings").setBalance(1000.0).setActive(true).build();

		BankAccount bankAccount2 = new BankAccount.BankAccountBuilder().setAccountNumber("987654321")
				.setAccountHolderName("Jane Smith").setAccountType("Checking").setBalance(500.0).setActive(false)
				.build();

		System.out.println("Bank Account 1: " + bankAccount1);
		System.out.println("Bank Account 2: " + bankAccount2);
	}
}
