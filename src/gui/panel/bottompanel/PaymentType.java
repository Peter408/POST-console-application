package gui.panel.bottompanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaymentType implements ActionListener {
    JRadioButton checkPanel;
    JRadioButton cashPanel;
    JRadioButton creditPanel;

    JPanel panel;

    Delegate delegate;

    interface Delegate {
        void paymentTypeClicked(ActionEvent e);
    }

    public PaymentType(Delegate delegate) {
        this.delegate = delegate;
        createPaymentType();
    }

    void createPaymentType() {
        JPanel paymentPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(paymentPanel, BoxLayout.Y_AXIS);
        paymentPanel.setLayout(boxLayout);
        paymentPanel.setBorder(new EmptyBorder(new Insets(0, 0, 50, 50)));

        ButtonGroup buttonGroup = new ButtonGroup();

        checkPanel = new JRadioButton();
        cashPanel = new JRadioButton();
        creditPanel = new JRadioButton();

        // check
        checkPanel = createRadioButton("Check");
        paymentPanel.add(checkPanel);
        checkPanel.addActionListener(this);
        checkPanel.setSelected(true);

        // cash
        cashPanel = createRadioButton("Cash");
        paymentPanel.add(cashPanel);
        cashPanel.addActionListener(this);

        // credit
        creditPanel = createRadioButton("Credit");
        paymentPanel.add(creditPanel);
        creditPanel.addActionListener(this);

        buttonGroup.add(checkPanel);
        buttonGroup.add(cashPanel);
        buttonGroup.add(creditPanel);

        this.panel = paymentPanel;
    }

    private JRadioButton createRadioButton(String name) {
        JRadioButton radioButton = new JRadioButton();
        radioButton.setText(name);
        return radioButton;
    }

    public JPanel getPanel() {
        return this.panel;
    }

    public enum PaymentTypeEnum {
        CREDIT, CASH, CHECK, NONE
    }

    public PaymentTypeEnum getSelected() {
        if (cashPanel.isSelected()) {
            return PaymentTypeEnum.CASH;
        } else if (checkPanel.isSelected()) {
            return PaymentTypeEnum.CHECK;
        } else if (creditPanel.isSelected()) {
            return PaymentTypeEnum.CREDIT;
        } else {
            return PaymentTypeEnum.NONE;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.delegate != null)
            this.delegate.paymentTypeClicked(e);
    }
}