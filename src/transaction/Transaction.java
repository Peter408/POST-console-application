package transaction;

import java.time.ZonedDateTime;

import user.Customer;

public class Transaction {
    Customer customer;
    Payment payment;
    ZonedDateTime dateTime;

    public Transaction(Customer customer, string paymentType, double payment, int cardNumber) {
        this.customer = customer;
        double total = customer.getTotal();
        this.payment = new Payment(paymentType, total, payment, cardNumber);
        dateTime = new ZonedDateTime.now();
    }

    public string getPaymentType() {
        return this.payment.getPaymentType();
    }

    public boolean getApproved() {
        return this.payment.getApproved();
    }

    public double getTotal() {
        return this.payment.getTotal();
    }

    public double getPayment() {
        return this.payment.getPayment();
    }

    public double getChange() {
        return this.getPayment.getChange();
    }

    public int getCardNumber() {
        return this.payment.getCardNumber();
    }

    public string getTimestamp() {
        return this.dateTime.toString();
    }
}