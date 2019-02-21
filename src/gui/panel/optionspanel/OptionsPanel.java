package gui.panel.optionspanel;


import javax.swing.*;
import java.awt.*;
import gui.panel.optionspanel.*;

public class OptionsPanel extends JPanel {
    private static final int MAX_WIDTH = 50;
    private static final int MAX_HEIGHT = 600;

    private Dimension dimension;
    private CloseStore closeStore;

    public interface Delegate {
         public void closeButtonClicked();
    }

    public OptionsPanel(CloseStore.Delegate delegate) {
        this.dimension = new Dimension(MAX_WIDTH, MAX_HEIGHT);
        this.setPreferredSize(dimension);
        this.setSize(dimension);

        closeStore = new CloseStore(delegate);

        this.add(closeStore.getPanel(), BorderLayout.NORTH);
    }
}
