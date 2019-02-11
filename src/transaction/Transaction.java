package transaction;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import user.Customer;

public class Transaction {
    private Customer customer;
    private Payment payment;
    private String today;

    public Transaction(Customer customer, String paymentType, double payment, String cardNumber) {
        this.customer = customer;
        double total = customer.getTotal();
        this.payment = new Payment(paymentType, total, payment, cardNumber);
        setDateTime();
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
        return this.today;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public String getFormattedCartList() {
        return this.customer.getCartItemList();
    }

    private void setDateTime() {
        DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' hh:mm a");
        LocalDateTime localDateTime = LocalDateTime.now();
        this.today = FOMATTER.format(localDateTime);
    }

    public String displayTransactionPayment() {
        StringBuffer SB = new StringBuffer();
        SB.append("Amount Tendered: " );
        switch(this.payment.getPaymentType()) {
            case "CASH":
                SB.append("$" + String.format("%.2f", this.payment.getPayment()) + "\n");
                SB.append("Amount Returned: $" + String.format("%.2f", this.payment.getChange()));
                break;
            case "CHECK":
                SB.append("Paid by check");
                break;
            case "CREDIT":
                SB.append("Credit Card " + this.payment.getCardNumber());
                break;
            default:
        }
        SB.append("\n");
        return SB.toString();
    }

    @Override
    public String toString() {
        StringBuffer SB = new StringBuffer();
        SB.append(this.customer.toString());
        SB.append(this.payment.toString());
        return SB.toString();
    }
}