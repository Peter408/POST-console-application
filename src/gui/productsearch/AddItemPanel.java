package gui.productsearch;

import item.Item;
import store.Catalog;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class AddItemPanel extends JPanel implements SearchBarPanel.Delegate{
    private static final int WIDTH = 600;
    private static final int HEIGHT = 125;

    private Catalog catalog;

    AddItemPanel(Catalog catalog) {
        this.catalog = catalog;
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
        ProductAdder productAdder = new ProductAdder(catalog);
        this.add(productAdder, constraints);
    }

    @Override
    public void filterResults(String query) {
        System.out.print(query);
        LinkedList<Item> results = new LinkedList<>();

        results.addAll(catalog.searchItemByName(query));
        results.addAll(catalog.searchItemByUPC(query));
        if(results!= null) { System.out.println(results); }
        //System.out.println(catalog);
    }
}
