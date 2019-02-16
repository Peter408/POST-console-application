package gui.panel.middlepanel;

import store.Catalog;
import gui.panel.middlepanel.cartitemgui.*;

import javax.swing.*;
import java.awt.*;

public class MiddlePanel extends JPanel {
    private static final int MAX_WIDTH = 800;
    private static final int MAX_HEIGHT = 400;

    private Catalog catalog;
    private Dimension dimension;
    private CartItemPanel cartItemPanel;

    public MiddlePanel(Catalog catalog){
        this.catalog = catalog;
        this.dimension = new Dimension( MAX_WIDTH, MAX_HEIGHT);
        this.setPreferredSize( dimension );
        this.setSize( dimension );
        this.setBackground(Color.GRAY);

        this.setLayout(new BorderLayout());
        this.add(MiddleHeader.createHeaderPanel(), BorderLayout.NORTH);
        cartItemPanel = new CartItemPanel(catalog);
        this.add(cartItemPanel);
    }
}
