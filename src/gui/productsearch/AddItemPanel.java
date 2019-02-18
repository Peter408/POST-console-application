package gui.productsearch;

import item.CartItem;
import item.Item;
import store.Catalog;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class AddItemPanel extends JPanel implements SearchBarPanel.Delegate, ProductAdder.Delegate {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 125;

    private Catalog catalog;
    private ProductTable productTable;
    private Delegate delegate;
    private CartItem itemAdded;

    public interface Delegate {
        void itemAddedToCart(CartItem cartItem);
    }

    AddItemPanel(Delegate delegate, Catalog catalog) {
        this.delegate = delegate;
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
        productTable = new ProductTable();
        this.add(productTable, constraints);

        constraints.gridy = 2;
        ProductAdder productAdder = new ProductAdder(this);
        this.add(productAdder, constraints);
    }

    @Override
    public void filterResults(String query) {
        System.out.print(query);
        List<Item> results = new LinkedList<>();

        results.addAll(catalog.searchItemByName(query));
        results.addAll(catalog.searchItemByUPC(query));
        if (results != null) {
            productTable.setData(results);
        }
    }

    @Override
    public void addSelectedProduct(int withQuantity) {
        Item item = productTable.getSelectedItem();
        System.out.println("adding" + item + " with quantity: " + withQuantity);
        if (withQuantity > 0) {
            itemAdded = new CartItem(item, withQuantity);
            if (this.delegate != null) {
                delegate.itemAddedToCart(itemAdded);
            }
        }
    }
}
