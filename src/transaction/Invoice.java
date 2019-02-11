package transaction;

public class Invoice {
    private Transaction transaction;

    public Invoice(Transaction transaction) {
        this.transaction = transaction;
    }

    public String displayInvoice() {
        StringBuffer SB = new StringBuffer();
        SB.append(this.transaction.getCustomer().getName() + " " + this.transaction.getTimestamp() + "\n");
        SB.append(this.transaction.getFormattedCartList());
        SB.append("-----\n");
        SB.append("Total $" + String.format("%.2f", transaction.getTotal()) + "\n");
        if(transaction.getApproved()) {
            SB.append(transaction.displayTransactionPayment());
        } else {
            SB.append(transaction.displayRejected());
        }
        SB.append("\n");
        return SB.toString();
    }
}