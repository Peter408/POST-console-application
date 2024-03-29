package gui.panel.bottompanel;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Checkout {
    JButton button;
    JPanel panel;

    CheckoutDelegate delegate;

    interface CheckoutDelegate {
        void checkoutButtonClicked(ActionEvent e);
    }

    public Checkout(CheckoutDelegate delegate) {
        this.delegate = delegate;
        createCheckout();
    }

    void createCheckout() {
        JPanel checkoutPanel = new JPanel();

        checkoutPanel.setLayout(new BorderLayout());

        button = new JButton();
        button.setText("Checkout");

        checkoutPanel.add(button, BorderLayout.SOUTH);

        // on button click
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (delegate != null)
                    delegate.checkoutButtonClicked(e);
            }
        });

        this.panel = checkoutPanel;
    }

    JPanel getPanel() {
        return this.panel;
    }
}