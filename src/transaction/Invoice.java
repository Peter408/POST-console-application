package transaction;

public class Invoice {
    Transaction transaction;

    public Invoice(Transaction transaction) {
        this.transaction = transaction;
    }

    public String displayInvoice() {
        StringBuffer SB = new StringBuffer();
        SB.append(this.transaction.getCustomer().getName() + " " + this.transaction.getTimestamp() + "\n\n");
        SB.append(this.transaction.getFormattedCartList());
        SB.append("Total $" + String.format("%.2f", transaction.getTotal()) + "\n");
        SB.append(transaction.displayTransactionPayment());
        return SB.toString();
    }
}