package gui.productsearch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SearchBarPanel extends JPanel {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 30;
    private Dimension dimension;
    private JTextField searchField;
    private JLabel searchLabel;
    private Delegate delegate;

    interface Delegate {
        void filterResults(String query);
    }

    public SearchBarPanel (){
        initialize();
    }

    public SearchBarPanel(Delegate delegate) {
        this.delegate = delegate;
        initialize();
    }

    public void initialize() {
        this.dimension = new Dimension(WIDTH, HEIGHT);
        this.setPreferredSize(dimension);
        this.setSize(dimension);
        // this.setBackground(Color.GREEN);
        initializeSubviews();
        this.add(searchLabel);
        this.add(searchField);
    }

    private void initializeSubviews() {
        this.searchLabel = new JLabel("Search: ");
        this.searchField = new JTextField();
        searchField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { }

            @Override
            public void keyPressed(KeyEvent e) { }

            @Override
            public void keyReleased(KeyEvent e) {
                if(delegate != null) {
                    delegate.filterResults(searchField.getText());
                }
            }
        });
        searchField.setColumns(40);
    }
}
