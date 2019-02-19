package gui.panel.bottompanel.paymentlistener;

public class CashListener extends PaymentListener {


    @Override
    public boolean checkValidation() {
        String input = this.inputTextField.getText();
        try {
            return Double.parseDouble(input) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public void setInputLabel() {
        this.inputLabel.setVisible(true);
        this.inputLabel.setText("Cash Amount: ");
    }
}
