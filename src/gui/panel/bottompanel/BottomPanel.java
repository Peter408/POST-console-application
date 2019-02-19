package gui.panel.bottompanel;

import javax.swing.*;

import gui.panel.bottompanel.Checkout.CheckoutDelegate;
import gui.panel.bottompanel.PaymentType.PaymentTypeEnum;

import java.awt.*;
import java.awt.event.ActionEvent;

public class BottomPanel extends JPanel implements CheckoutDelegate {
    private static final int MAX_WIDTH = 800;
    private static final int MAX_HEIGHT = 125;

    private Dimension dimension;
    private PaymentType paymentType;
    private Checkout checkout;
    private Total total;
    private Delegate delegate;

    public interface Delegate {
        // TODO add payment
        void checkout(PaymentTypeEnum paymentType);
    }

    public BottomPanel() {
        this.initializeView();
    }

    public BottomPanel(Delegate delegate) {
        this.delegate = delegate;
        this.initializeView();
    }

    private void initializeView() {
        this.dimension = new Dimension(MAX_WIDTH, MAX_HEIGHT);
        this.setPreferredSize(dimension);
        this.setSize(dimension);

        this.setLayout(new BorderLayout());

        // create instance
        paymentType = new PaymentType();
        checkout = new Checkout(this);
        total = new Total();

        paymentType.createPaymentType();
        checkout.createCheckout();
        total.createTotal();

        this.add(paymentType.getPanel(), BorderLayout.WEST);
        this.add(checkout.getPanel(), BorderLayout.EAST);
        this.add(total.getPanel(), BorderLayout.NORTH);
    }

    public void checkoutButtonClicked(ActionEvent e) {
        if (delegate != null) {
            delegate.checkout(paymentType.getSelected());
        }
    }

}
