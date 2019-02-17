package gui.productsearch;

import store.Catalog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchBarPanel extends JPanel implements ActionListener {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 75;
    private Dimension dimension;

    private JTextField textField;
    private JLabel label;

    Catalog catalog;

    private Delegate delegate;

    interface Delegate {
        void filterResults(String query);
    }

    SearchBarPanel(Catalog catalog) {
        this.catalog = catalog;
        initialize();
    }

    public void initialize() {
        this.dimension = new Dimension(WIDTH, HEIGHT);
        this.setPreferredSize(dimension);
        this.setSize(dimension);
        // this.setBackground(Color.GREEN);
        initializeSubviews();
    }

    SearchBarPanel(Delegate delegate) {
        this.delegate = delegate;
        initializeSubviews();
    }

    private void initializeSubviews() {
        this.textField = new JTextField();
        this.label = new JLabel();
        this.label.setText("Search:");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.delegate == null)
            return;
        System.out.println("asdfasdfasdf");
        delegate.filterResults(this.textField.getText());
    }

}
