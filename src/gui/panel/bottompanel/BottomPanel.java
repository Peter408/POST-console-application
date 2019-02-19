package gui.panel.bottompanel;

import javax.swing.*;

import gui.panel.bottompanel.Checkout.CheckoutDelegate;
import gui.panel.bottompanel.PaymentType.PaymentTypeEnum;
import gui.panel.bottompanel.paymentlistener.*;

import java.awt.*;
import java.awt.event.ActionEvent;

public class BottomPanel extends JPanel implements CheckoutDelegate, PaymentType.Delegate {
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

    public interface Delegate {
        // TODO add payment
        void checkout(PaymentTypeEnum paymentType, String associatedValue);
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
        paymentType = new PaymentType(this);
        checkout = new Checkout(this);
        totalPanel = new Total();

        paymentType.createPaymentType();
        checkout.createCheckout();
        totalPanel.createTotal();

        this.add(paymentType.getPanel(), BorderLayout.WEST);
        this.add(checkout.getPanel(), BorderLayout.EAST);
        this.add(totalPanel.getPanel(), BorderLayout.NORTH);

        this.cashListener = new CashListener();
        this.checkListener = new CheckListener();
        this.creditListener = new CreditListener();
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

}
