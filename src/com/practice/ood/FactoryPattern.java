package com.practice.ood;

// Payment Interface
interface Payment {
	void processPayment(double amount);
}

// Concrete Payment - Credit Card Payment
class CreditCardPayment implements Payment {
	@Override
	public void processPayment(double amount) {
		System.out.println("Processing credit card payment of $" + amount);
	}
}

// Concrete Payment - Debit Card Payment
class DebitCardPayment implements Payment {
	@Override
	public void processPayment(double amount) {
		System.out.println("Processing debit card payment of $" + amount);
	}
}

// Concrete Payment - Cash Payment
class CashPayment implements Payment {
	@Override
	public void processPayment(double amount) {
		System.out.println("Processing cash payment of $" + amount);
	}
}

// Payment Factory
class PaymentFactory {
	public Payment createPayment(String paymentType) {
		switch (paymentType) {
		case "creditCard":
			return new CreditCardPayment();
		case "debitCard":
			return new DebitCardPayment();
		case "cash":
			return new CashPayment();
		default:
			throw new IllegalArgumentException("Invalid payment type: " + paymentType);
		}
	}
}

// Client code using the Payment Factory
public class FactoryPattern {
	public static void main(String[] args) {
		PaymentFactory paymentFactory = new PaymentFactory();

		Payment creditCardPayment = paymentFactory.createPayment("creditCard");
		creditCardPayment.processPayment(100.0);

		Payment debitCardPayment = paymentFactory.createPayment("debitCard");
		debitCardPayment.processPayment(50.0);

		Payment cashPayment = paymentFactory.createPayment("cash");
		cashPayment.processPayment(20.0);
	}
}
