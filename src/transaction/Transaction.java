package transaction;

import java.time.LocalDateTime;

import user.Customer;

public class Transaction {
    Customer customer;
    Payment payment;
    LocalDateTime today;

    public Transaction(Customer customer, String paymentType, double payment, String cardNumber) {
        this.customer = customer;
        double total = customer.getTotal();
        this.payment = new Payment(paymentType, total, payment, cardNumber);
        today = LocalDateTime.now();
    }

    public Transaction(Customer customer, String paymentType, double payment) {
        this(customer, paymentType, payment, "");
    }

    public Transaction(Customer customer, String paymentType, String cardNumber) {
        this(customer, paymentType, 0, cardNumber);
    }

    public String getPaymentType() {
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
        return this.payment.getChange();
    }

    public String getCardNumber() {
        return this.payment.getCardNumber();
    }

    public String getTimestamp() {
        return this.today.toString();
    }

    // public void getTransaction() {
    //     StringBuffer SB = new StringBuffer();
    //     SB.append(this.customer.getName());
    //     this.customer.getCart().
    // }

    @Override
    public String toString() {
        StringBuffer SB = new StringBuffer();
        SB.append(this.customer.toString());
        SB.append(this.payment.toString());
        return SB.toString();
    }
}