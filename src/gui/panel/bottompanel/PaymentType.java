package gui.panel.bottompanel;

import javax.swing.*;

import java.awt.*;

public class PaymentType {
    private static final int MAX_WIDTH = 400; // change this shit cuz im stupid
    private static final int TEXT_HEIGHT = 30;
    
    static JPanel createPaymentType() {
        GridBagConstraints constraints = setConstraints();

        JPanel paymentPanel = new JPanel();
        paymentPanel.setLayout(new GridBagLayout());

        // check
        // or make as JPanal and add to it from the function
        JRadioButton checkPanel = createRadioButton("Check");
        paymentPanel.add(checkPanel, constraints);
        // cash
        // credit
        
        return paymentPanel;
    }

    private static GridBagConstraints setConstraints() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        return constraints;
    }

    private static JRadioButton createRadioButton(String name) {
        JRadioButton radioButton = new JRadioButton();
        radioButton.setText(name);
        // set bound ?
        // radioButton.setBounds(0, 25, 25, 25); 
        return radioButton;
    }
}