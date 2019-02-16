package gui.panel.middlepanel.cartitemgui;

import javax.swing.JPanel;

import item.*;
import store.Catalog;

public class CartItemPanel extends JPanel {
    CartItem cartItem;
    Catalog catalog;

    public CartItemPanel(Catalog catalog) {
        this.catalog = catalog;
    }

    public void addItem(Item item, int quantity) {

    }

    public void createAddItemWindow() {
        new AddItemWindow(this, catalog);
    }
}
