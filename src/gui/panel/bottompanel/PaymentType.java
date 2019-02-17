package gui.panel.bottompanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class PaymentType { 
    JRadioButton checkPanel;
    JRadioButton cashPanel;
    JRadioButton creditPanel;

    JPanel createPaymentType() {
        JPanel paymentPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(paymentPanel, BoxLayout.Y_AXIS);
        paymentPanel.setLayout(boxLayout);
        paymentPanel.setBorder(new EmptyBorder(new Insets(0, 0 , 50, 50)));

        ButtonGroup buttonGroup = new ButtonGroup();

        checkPanel = new JRadioButton();
        cashPanel = new JRadioButton();
        creditPanel = new JRadioButton();

        // check
        checkPanel = createRadioButton("Check");
        paymentPanel.add(checkPanel);

        // cash
        cashPanel = createRadioButton("Cash");
        paymentPanel.add(cashPanel);
    
        // credit
        creditPanel = createRadioButton("Credit");
        paymentPanel.add(creditPanel);

        buttonGroup.add(checkPanel);
        buttonGroup.add(cashPanel);
        buttonGroup.add(creditPanel);
        
        return paymentPanel;
    }

    private JRadioButton createRadioButton(String name) {
        JRadioButton radioButton = new JRadioButton();
        radioButton.setText(name); 
        return radioButton;
    }

    enum PaymentTypeEnum {
        CREDIT, CASH, CHECK, NONE
    }

    public PaymentTypeEnum getSelected() {
        if(cashPanel.isSelected()) {
            return PaymentTypeEnum.CASH;
        } else if(checkPanel.isSelected()){
            return PaymentTypeEnum.CHECK;
        } else if(creditPanel.isSelected()){
            return PaymentTypeEnum.CREDIT;
        } else {
            return PaymentTypeEnum.NONE;
        }
    }
}