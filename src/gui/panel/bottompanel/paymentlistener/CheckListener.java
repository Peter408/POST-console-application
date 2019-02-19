package gui.panel.bottompanel.paymentlistener;

public class CheckListener extends PaymentListener {
    public boolean checkValidation() {
        return true;
    }
    public void setInputLabel() {
        this.setVisible(false);
    }
}