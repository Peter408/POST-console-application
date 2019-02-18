package gui.productsearch;

import store.Catalog;

import javax.swing.*;

public class AddItemFrame extends JFrame {
    Catalog catalog;

    public AddItemFrame(Catalog catalog) {
        this.catalog = catalog;

        setResizable(false);
        this.add(new AddItemPanel(catalog));
        setVisible(true);
        this.pack();
    }
}
