package gui.panel.middlepanel;

import gui.productsearch.AddItemPanel;
import item.Cart;
import store.Catalog;
import gui.panel.middlepanel.cartitemgui.*;

import javax.swing.*;
import java.awt.*;

public class MiddlePanel extends JPanel {
    private static final int MAX_WIDTH = 800;
    private static final int MAX_HEIGHT = 400;

    private Dimension dimension;

    public MiddlePanel(AddItemPanel.Delegate delegate, Catalog catalog, Cart cart) {
        this.dimension = new Dimension(MAX_WIDTH, MAX_HEIGHT);
        this.setPreferredSize(dimension);
        this.setSize(dimension);
        this.setBackground(Color.GRAY);
        this.setLayout(new GridLayout(1, 1));
        this.add(new CartItemPanel(delegate, catalog, cart));
    }
}
