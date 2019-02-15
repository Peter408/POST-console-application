package gui;

import javax.swing.*;
import java.awt.*;

public class MiddlePanel extends JPanel {
    private static final int MAX_WIDTH = 800;
    private static final int MAX_HEIGHT = 400;
    private static final int ITEM_GAP = 5;

    private Dimension dimension;

    public MiddlePanel(){
        this.dimension = new Dimension( MAX_WIDTH, MAX_HEIGHT);
        this.setPreferredSize( dimension );
        this.setSize( dimension );
        this.setBackground(Color.BLACK);
        this.setFocusable( true );
        this.requestFocusInWindow();
    }

}
