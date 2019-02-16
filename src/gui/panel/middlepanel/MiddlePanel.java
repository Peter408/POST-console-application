package gui.panel.middlepanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MiddlePanel extends JPanel {
    private static final int MAX_WIDTH = 800;
    private static final int MAX_HEIGHT = 400;

    private Dimension dimension;

    public MiddlePanel(){
        this.dimension = new Dimension( MAX_WIDTH, MAX_HEIGHT);
        this.setPreferredSize( dimension );
        this.setSize( dimension );
        this.setBackground(Color.GRAY);

        this.setLayout(new BorderLayout());
        this.add(MiddleHeader.createHeaderPanel(), BorderLayout.NORTH);
    }
}
