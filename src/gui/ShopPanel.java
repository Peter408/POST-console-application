package gui;

import gui.panel.bottompanel.BottomPanel;
import gui.panel.bottompanel.PaymentType;
import gui.panel.bottompanel.PaymentType.PaymentTypeEnum;
import gui.panel.middlepanel.MiddlePanel;
import gui.panel.middlepanel.cartitemgui.CartItemPanel;
import gui.panel.optionspanel.OptionsPanel;
import gui.panel.optionspanel.CloseStore;
import gui.panel.toppanel.TopPanel;
import gui.productsearch.AddItemPanel;
import item.*;
import post.POST;
import transaction.Payment;
import transaction.Transaction;
import user.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ShopPanel extends JPanel
        implements AddItemPanel.Delegate, CartItemPanel.Delegate, TopPanel.Delegate, BottomPanel.Delegate
        , CloseStore.Delegate, Resettable {
    private TopPanel topPanel;
    private MiddlePanel middlePanel;
    private BottomPanel bottomPanel;
    private OptionsPanel optionsPanel;

    private Customer customer;
    private POST post;

    public ShopPanel(POST post) {
        this.post = post;
        this.customer = new Customer("");

        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = 3;


        optionsPanel = new OptionsPanel(this);
        this.add(optionsPanel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridheight = 1;

        topPanel = new TopPanel(this);
        this.add(topPanel, constraints);

        constraints.gridy = 1;
        middlePanel = new MiddlePanel(this, this, post.getCatalog(), customer.getCart());
        this.add(middlePanel, constraints);

        constraints.gridy = 2;
        bottomPanel = new BottomPanel(this, customer.getCart());
        this.add(bottomPanel, constraints);
    }

    public String getCustomerName() {
        return topPanel.getCustomerName();
    }

    @Override
    public void nameChanged(String name) {
        customer.setName(name);
    }

    @Override
    public void itemAddedToCart(CartItem cartItem) {
        customer.getCart().add(cartItem);
    }

    @Override
    public void itemRemovedFromCart(Item item) {
        customer.removeFromCart(item);
    }

    @Override
    public void checkout(PaymentType.PaymentTypeEnum paymentType, String associatedValue) {
        Payment payment;
        Customer customer = this.customer;
        if (customer.getCart().isEmpty()) {
            return;
        }
        double total = customer.getTotal();
        switch (paymentType) {
            case CASH:
                payment = new Payment("CASH", total, Double.parseDouble(associatedValue), null);
                break;
            case CHECK:
                payment = new Payment("CHECK", total, null);
                break;
            case CREDIT:
                payment = new Payment("CREDIT", total, associatedValue);
                break;
            default:
                return;
        }
        boolean valid = this.post.validatePayment(payment);
        if (valid) {
            Transaction transaction = this.post.checkout(customer, payment);
            this.reset();
        } else {
            JOptionPane.showMessageDialog(this, "Not enough money");
            System.err.println("invalid payment: " + payment);
        }
    }

    @Override
    public void closeButtonClicked() {
        System.out.println("closed store");
        post.closeStore();
        System.exit(0);
    }

    public void reset() {
        this.customer.setName("");
        this.customer.getCart().clearCart();
        this.topPanel.reset();
        this.bottomPanel.reset();
    }
}
