package gui.panel.productsearch;

import store.Catalog;

import javax.swing.*;
import java.awt.*;

public class ProductAdder extends JPanel {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 75;
    private Dimension dimension;

    Catalog catalog;
    ProductAdder (Catalog catalog){
        this.catalog = catalog;
        initialize();
    }

    public void initialize(){
        this.dimension = new Dimension( WIDTH, HEIGHT);
        this.setPreferredSize( dimension );
        this.setSize( dimension );
        this.setBackground(Color.BLUE);
    }
}
