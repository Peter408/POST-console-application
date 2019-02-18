package gui.panel.bottompanel;

import javax.swing.*;

import gui.panel.bottompanel.Checkout.CheckoutDelegate;

import java.awt.*;
import java.awt.event.ActionEvent;

public class BottomPanel extends  JPanel implements CheckoutDelegate {
    private static final int MAX_WIDTH = 800;
    private static final int MAX_HEIGHT = 125;

    private Dimension dimension;
    private PaymentType paymentType;
    private Checkout checkout;
    private Total total;

    public BottomPanel() {
        this.dimension = new Dimension( MAX_WIDTH, MAX_HEIGHT);
        this.setPreferredSize( dimension );
        this.setSize( dimension );

        this.setLayout(new BorderLayout());

        // create instance
        paymentType = new PaymentType();
        checkout = new Checkout(this);
        total = new Total();

        paymentType.createPaymentType();
        checkout.createCheckout();
        total.createTotal();

        // add payenttype
        this.add(paymentType.getPanel(), BorderLayout.WEST);

        // add total
        this.add(total.getPanel(), BorderLayout.NORTH);

        // add checkouts
        this.add(checkout.getPanel(), BorderLayout.EAST);
    }

    public void checkoutButtonClicked(ActionEvent e) {
        switch(paymentType.getSelected()) {
            case CASH:
                System.out.println("You clicked CASH");
                break;
            case CHECK:
                System.out.println("You clicked CHECK");
                break;
            case CREDIT:
                System.out.println("You clicked CREDIT");
                break;
            case NONE:
                System.out.println("You clicked NONE");
                break;
        }
    }

}
