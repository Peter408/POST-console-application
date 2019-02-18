package gui;

import gui.panel.bottompanel.BottomPanel;
import gui.panel.middlepanel.MiddlePanel;
import gui.panel.optionspanel.OptionsPanel;
import gui.panel.toppanel.TopPanel;
import gui.productsearch.AddItemPanel;
import item.CartItem;
import post.POST;
import user.Customer;

import javax.swing.*;
import java.awt.*;

public class ShopPanel extends JPanel implements AddItemPanel.Delegate{
    private TopPanel topPanel = new TopPanel();
    private MiddlePanel middlePanel;
    private BottomPanel bottomPanel = new BottomPanel();

    private Customer customer;
    private POST post;

    public ShopPanel(POST post){
        this.post = post;
        this.customer = new Customer("");

        this.setLayout( new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx =0;
        constraints.gridy = 0;
        constraints.gridheight = 3;

        this.add(new OptionsPanel(), constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridheight = 1;

        this.add(topPanel, constraints);

        constraints.gridy = 1;
        middlePanel = new MiddlePanel(this, post.getCatalog());
        this.add(middlePanel, constraints);

        constraints.gridy = 2;
        this.add(bottomPanel, constraints);
    }

    public String getCustomerName() {
        return topPanel.getCustomerName();
    }

    @Override
    public void itemAddedToCart( CartItem cartItem ){
        customer.addToCart(cartItem);
    }
}

