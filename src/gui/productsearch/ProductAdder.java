package gui.productsearch;

import store.Catalog;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductAdder extends JPanel implements ActionListener {
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
        initialize();
    }

    ProductAdder(Delegate delegate) {
        this.delegate = delegate;
        initialize();
    }

    public void initialize() {
        this.dimension = new Dimension(WIDTH, HEIGHT);
        this.setPreferredSize(dimension);
        this.setSize(dimension);
        this.initializeSubviews();
    }

    private void initializeSubviews() {
        this.addButton = new JButton("Add");
        this.quantityLabel = new JLabel("Quantity: ");
        this.quantityField = new JTextField("1");

        this.quantityField.setColumns(5);
        this.addButton.addActionListener(this);

        this.add(quantityLabel);
        this.add(quantityField);
        this.add(addButton);
    }

    private int getQuantity() throws NumberFormatException {
        String text = this.quantityField.getText();
        if (text != null) {
            return Integer.parseInt(text);
        }
        return 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (delegate == null)
            return;
        try {
            int quantity = getQuantity();
            delegate.addSelectedProduct(quantity);
            this.quantityField.setBorder(null);
        } catch (NumberFormatException error) {
            LineBorder border = new LineBorder(Color.RED);
            this.quantityField.setBorder(border);
            return;
        }
    }
}
