package gui.productsearch;

import item.CartItem;
import store.Catalog;

import javax.swing.*;

public class AddItemFrame extends JFrame {
    Catalog catalog;

    public AddItemFrame(AddItemPanel.Delegate delegate ,Catalog catalog) {
        this.catalog = catalog;

        setResizable(false);
        this.add(new AddItemPanel(delegate, catalog));
        setVisible(true);
        this.pack();
    }
}
