package gui.panel.bottompanel.paymentlistener;

public class CreditListener extends PaymentListener {

    @Override
    public boolean checkValidation(){
        //validate here
        return true;
    }

    @Override
    public void setInputLabel(){
        this.inputLabel.setVisible(true);
        this.inputLabel.setText("Credit Card Number: ");
    }
}
