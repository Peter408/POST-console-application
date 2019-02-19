package gui.panel.bottompanel.paymentlistener;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

abstract public class PaymentListener extends JPanel {
    JLabel inputLabel;
    JTextField inputTextField;
    JPanel paymentPanel;

    //public JPanel getPanel(){
    //    return this.paymentPanel;
    //}

    public PaymentListener(){
        initialize();
    }

    private void initialize() {
        this.paymentPanel = new JPanel();
        this.inputLabel = new JLabel();
        setInputLabel();
        this.inputTextField = new JTextField();

        inputTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { }

            @Override
            public void keyPressed(KeyEvent e) { }

            @Override
            public void keyReleased(KeyEvent e) {
                String input = inputTextField.getText();
                if( PaymentListener.this.checkValidation() ){
                    inputTextField.setBorder(new LineBorder(null));
                    System.out.println(inputTextField.getText());
                    //if(delegate != null) {
                    //    delegate.applyPayment(inputTextField.getText());
                    //}
                } else {
                    inputTextField.setBorder(new LineBorder(Color.RED));
                }
            }
        });
        inputTextField.setColumns(15);

        paymentPanel.add(inputLabel);
        paymentPanel.add(inputTextField);
        add(paymentPanel);
    }

    public String getInputValue() {
        return this.inputTextField.getText();
    }

    abstract public boolean checkValidation();
    abstract public void setInputLabel();
}
