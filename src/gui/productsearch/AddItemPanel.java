package gui.productsearch;

import store.Catalog;

import javax.swing.*;
import java.awt.*;

public class AddItemPanel extends JPanel implements SearchBarPanel.Delegate, ProductAdder.Delegate {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 125;

    AddItemPanel(Catalog catalog) {
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = 1;

        SearchBarPanel searchBarPanel = new SearchBarPanel(this);
        this.add(searchBarPanel, constraints);

        constraints.gridy = 1;
        ProductTable productTable = new ProductTable(catalog);
        this.add(productTable, constraints);

        constraints.gridy = 2;
        ProductAdder productAdder = new ProductAdder(this);
        this.add(productAdder, constraints);
    }

    @Override
    public void filterResults(String query) {
        System.out.print(query);
    }

    @Override
    public void addSelectedProduct(int withQuantity) {
        System.out.println("adding item with quantity: " + withQuantity);
    }
}
