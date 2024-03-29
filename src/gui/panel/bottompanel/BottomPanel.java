package gui.panel.bottompanel;

import javax.swing.*;

import gui.Resettable;
import gui.panel.bottompanel.Checkout.CheckoutDelegate;
import gui.panel.bottompanel.PaymentType.PaymentTypeEnum;
import gui.panel.bottompanel.paymentlistener.*;
import item.Cart;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class BottomPanel extends JPanel implements CheckoutDelegate, PaymentType.Delegate, PropertyChangeListener, Resettable {
    private static final int MAX_WIDTH = 800;
    private static final int MAX_HEIGHT = 125;

    private Dimension dimension;
    private PaymentType paymentType;
    private Checkout checkout;
    private Total totalPanel;

    private PaymentListener paymentListener;
    private CashListener cashListener;
    private CheckListener checkListener;
    private CreditListener creditListener;

    private Delegate delegate;
    private Cart cart;

    public interface Delegate {
        // TODO add payment
        void checkout(PaymentTypeEnum paymentType, String associatedValue);
    }

    public BottomPanel() {
        this.initializeView();
    }

    public BottomPanel(Delegate delegate, Cart cart) {
        this.delegate = delegate;
        this.cart = cart;
        cart.addPropertyChangeListener(this);
        this.initializeView();
    }

    private void initializeView() {
        this.dimension = new Dimension(MAX_WIDTH, MAX_HEIGHT);
        this.setPreferredSize(dimension);
        this.setSize(dimension);

        this.setLayout(new BorderLayout());

        // create instance
        paymentType = new PaymentType(this);
        checkout = new Checkout(this);
        totalPanel = new Total();

        this.add(paymentType.getPanel(), BorderLayout.WEST);
        this.add(checkout.getPanel(), BorderLayout.EAST);
        this.add(totalPanel.getPanel(), BorderLayout.NORTH);

        this.cashListener = new CashListener();
        this.checkListener = new CheckListener();
        this.creditListener = new CreditListener();
        changePaymentListener(this.checkListener);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        double newValue = this.cart.getTotalCost();
        this.totalPanel.setTotal(String.format("$%.2f", newValue));
    }

    public void checkoutButtonClicked(ActionEvent e) {
        if (delegate != null) {
            boolean valid = paymentListener.checkValidation();
            if(valid)
                delegate.checkout(paymentType.getSelected(), paymentListener.getInputValue());
        }
    }

    private void changePaymentListener(PaymentListener paymentListener) {
        if(this.paymentListener != null){
            this.remove(this.paymentListener);
        }
        this.paymentListener = paymentListener;
        this.add(this.paymentListener, BorderLayout.CENTER);
        this.updateUI();
    }

    @Override
    public void paymentTypeClicked(ActionEvent e) {
        PaymentTypeEnum paymentType = this.paymentType.getSelected();
        
        switch(paymentType) {
            case CHECK:
                changePaymentListener(this.checkListener);
                break;
            case CASH:
                changePaymentListener(this.cashListener);
                break;
            case CREDIT:
                changePaymentListener(this.creditListener);
                break;
            default:
                System.out.println("DEFULAT CASE");

        }
    }

    @Override
    public void reset() {
        this.paymentListener.reset();
    }

    public void setChange(double changeDue) {
        String change = String.format("$%.2f", changeDue);
        this.totalPanel.setChange(change);
    }
}
