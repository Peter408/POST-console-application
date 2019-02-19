package gui.panel.bottompanel.paymentlistener;

public class CashListener extends  PaymentListener {


    @Override
    public boolean checkValidation( String input){
        //validate here
        return true;
    }

    @Override
    public void setInputLabel(){
        this.inputLabel.setText("Cash Amount: ");
    }
}
