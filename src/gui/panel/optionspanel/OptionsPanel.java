package gui.panel.optionspanel;


import javax.swing.*;
import java.awt.*;

public class OptionsPanel extends JPanel {
    private static final int MAX_WIDTH = 50;
    private static final int MAX_HEIGHT = 600;

    private Dimension dimension;
    private CloseStore closeStore;

    public OptionsPanel() {
        this.dimension = new Dimension(MAX_WIDTH, MAX_HEIGHT);
        this.setPreferredSize(dimension);
        this.setSize(dimension);
        //this.setBackground(Color.LIGHT_GRAY);

        closeStore = new CloseStore();

        this.add(closeStore.getPanel(), BorderLayout.NORTH);
    }
}
