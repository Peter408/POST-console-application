package transaction;

enum PaymentType {
    CASH, CREDIT, CHECK;
}

public class Payment {
    PaymentType paymentType;
    boolean approved;
    double total;
    double payment;
    double change;
    String cardNumber;

    public Payment(String paymentType, double total, double payment, String cardNumber) {
        this.paymentType = PaymentType.valueOf(paymentType);
        this.total = total;
        this.payment = payment;
        this.cardNumber = cardNumber;
        pay();
    }

    public Payment(String paymentType, double total, double payment) {
        this(paymentType, total, payment, "");
    }

    public Payment(String paymentType, double total, String cardNumber) {
        this(paymentType, total, 0, cardNumber);
    }

    private void pay() {
        switch(this.paymentType) {
            case CASH:
                chargeCash();
                break;
            case CREDIT:
                chargeCard();
                break;
            case CHECK:
                chargeCheck();
                break;
            default:
                System.out.println("something fucked up");
                break;
        }
    }

    private void chargeCard() {
        if(validateCard()) {
            this.approved = true;
        } else {
            this.approved = false;
        }
    }

    private void chargeCash() {
        if(this.payment >= this.total) {
            this.change = this.payment - this.total;
            this.approved = true;
        } else {
            this.approved = false;
        }
    }

    private void chargeCheck() {
        if(this.payment >= this.total) {
            this.change = this.payment - this.total;
            this.approved = true;
        } else {
            this.approved = false;
        }
    }

    private boolean validateCard() {
        if(this.cardNumber.length() == 5 || this.cardNumber.matches("[0-9]+")) {
            return true;
        }
        return false;
    }

    public String getPaymentType() {
        return this.paymentType.toString();
    }

    public boolean getApproved() {
        return this.approved;
    }

    public double getTotal() {
        return this.total;
    }

    public double getPayment() {
        return this.payment;
    }

    public double getChange() {
        return this.change;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    private void printApproved(StringBuffer SB) {
        switch(this.paymentType) {
            case CASH:
                SB.append("$" + this.payment);
                break;
            case CHECK:
                SB.append("$" + this.payment);
                break;
            case CREDIT:
                SB.append(this.cardNumber);
                break;
            default:
        }
    }

    @Override
    public String toString() {
        StringBuffer SB = new StringBuffer();
        SB.append("<" + this.paymentType + " ");
        if(this.approved) {
            printApproved(SB);
        } else {
            SB.append("Rejected");
        }
        SB.append(">");
        return SB.toString();
    }
}