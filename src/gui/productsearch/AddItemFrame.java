package gui.productsearch;

import item.CartItem;
import store.Catalog;

import javax.swing.*;

public class AddItemFrame extends JFrame {

    private static AddItemFrame instance;

    public static AddItemFrame getInstance(AddItemPanel.Delegate delegate, Catalog catalog) {
        if (AddItemFrame.instance == null)
            AddItemFrame.instance = new AddItemFrame(delegate, catalog);
        AddItemFrame.instance.toFront();
        return AddItemFrame.instance;
    }

    private AddItemFrame(AddItemPanel.Delegate delegate, Catalog catalog) {
        setResizable(false);
        this.add(new AddItemPanel(delegate, catalog));
        setVisible(true);
        this.pack();
    }

}
