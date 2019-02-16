package gui.panel.bottompanel;

import javax.swing.*;
import java.awt.*;

public class BottomPanel extends  JPanel{
    private static final int MAX_WIDTH = 800;
    private static final int MAX_HEIGHT = 125;
    private static final int TEXT_HEIGHT = 30;
    private static final int FONT_SIZE = 14;

    private Dimension dimension;

    public BottomPanel() {
        this.dimension = new Dimension( MAX_WIDTH, MAX_HEIGHT);
        this.setPreferredSize( dimension );
        this.setSize( dimension );
        this.setBackground(Color.LIGHTBLUE);

        this.setLayout(new BorderLayout());

    }

    private static JFrame createPaymentTypePanel() {
        
    }
}
