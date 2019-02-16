package gui.panel.middlepanel.cartitemgui;

import store.Catalog;

import javax.swing.JFrame;

public class AddItemWindow extends JFrame {
    private CartItemPanel cartItemPanel;
    private Catalog catalog;

    public AddItemWindow(CartItemPanel cartItemPanel, Catalog catalog) {
        this.cartItemPanel = cartItemPanel;
        this.catalog = catalog;
    }
}
