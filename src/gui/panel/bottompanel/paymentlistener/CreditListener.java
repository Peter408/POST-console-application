package gui.panel.bottompanel.paymentlistener;

public class CreditListener extends PaymentListener {

    @Override
    public boolean checkValidation(){
        try {
            Integer.parseInt(this.inputTextField.getText());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public void setInputLabel(){
        this.inputLabel.setVisible(true);
        this.inputLabel.setText("Credit Card Number: ");
    }
}
