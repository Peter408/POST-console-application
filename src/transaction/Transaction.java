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
    }

    // setters and getters coming in version 1.1
}