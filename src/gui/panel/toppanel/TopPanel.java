package gui.panel.toppanel;

import javax.swing.*;
import java.awt.*;

public class TopPanel extends JPanel {

    private static final int MAX_WIDTH = 800;
    private static final int MAX_HEIGHT = 75;
    private static final int ITEM_GAP = 5;

    private Dimension dimension;

    public TopPanel() {
        this.dimension = new Dimension(MAX_WIDTH, MAX_HEIGHT);
        this.setPreferredSize(dimension);
        this.setSize(dimension);
        this.setBackground(Color.CYAN);
    }
}
