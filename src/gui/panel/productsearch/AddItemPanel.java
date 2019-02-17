package gui.panel.productsearch;

import store.Catalog;

import javax.swing.*;
import java.awt.*;

public class AddItemPanel extends JPanel {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 125;

    AddItemPanel(Catalog catalog){
        this.setLayout( new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx =0;
        constraints.gridy = 0;
        constraints.gridheight = 1;

        SearchBarPanel searchBarPanel = new SearchBarPanel(catalog);
        this.add(searchBarPanel, constraints);

        constraints.gridy = 1;
        ProductTable productTable = new ProductTable(catalog);
        this.add(productTable, constraints);

        constraints.gridy = 2;
        ProductAdder productAdder = new ProductAdder(catalog);
        this.add(productAdder, constraints);
    }
}

