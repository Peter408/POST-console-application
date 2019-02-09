package transaction;

enum PaymentType {
    CASH, CARD, CHECK;
}

public class Payment {
    PaymentType paymentType;
    boolean approved;
    double total;
    double payment;
    double change;
    int cardNumber;

    public Payment(string paymentType, double total, double payment, int cardNumber) {
        this.paymentType = PaymentType.valueOf(paymentType); 
        this.total = total;
        this.payment = payment;
        this.cardNumber = cardNumber;
        pay();
    }

    private void pay() {
        switch(this.paymentType) {
            case CASH:
                chargeCash();
                break;
            case CARD:
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

    // TODO: not sure how card is charge? infinite about?
    private void chargeCard() {
        if(this.payment >= this.total) {
            this.approved = true;
        }
        this.approved = false;
    }

    private void chargeCash() {
        if(this.payment >= this.total) {
            this.change = this.payment - this.total;
            this.approved = true;
        } else {
            this.approved = false;
        }
    }

    // TODO: stuff
    private void chargeCheck() {
        // idk how this shit works
    }

    public string getPaymentType() {
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

    public int getCardNumber() {
        return this.cardNumber;
    }
}