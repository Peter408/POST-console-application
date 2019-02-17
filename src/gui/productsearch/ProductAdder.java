package gui.productsearch;

import store.Catalog;

import javax.swing.*;
import java.awt.*;

public class ProductAdder extends JPanel {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 75;
    private Dimension dimension;

    private JLabel quantityLabel;
    private JTextField quantityField;
    private JButton addButton;

    interface Delegate {
        void addSelectedProduct(int withQuantity);
    }

    private Delegate delegate;

    ProductAdder() {
        this.delegate = null;
    }

    ProductAdder(Delegate delegate) {
        this.delegate = delegate;
        initialize();
    }

    public void initialize() {
        this.dimension = new Dimension(WIDTH, HEIGHT);
        this.setPreferredSize(dimension);
        this.setSize(dimension);
        this.setBackground(Color.BLUE);
        this.initializeSubviews();
    }

    private void initializeSubviews() {
        this.addButton = new JButton("Add");
        this.quantityLabel = new JLabel("Quantity: ");
        this.quantityField = new JTextField();

        this.add(quantityLabel);
        this.add(quantityField);
        this.add(addButton);
    }
}
