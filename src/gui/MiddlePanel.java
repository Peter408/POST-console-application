package gui;

import javax.swing.*;
import java.awt.*;

public class MiddlePanel extends JPanel {
    private static final int MAX_WIDTH = 800;
    private static final int MAX_HEIGHT = 400;
    private static final int TEXT_HEIGHT = 30;
    private static final int FONT_SIZE = 14;

    private Dimension dimension;

    public MiddlePanel(){
        this.dimension = new Dimension( MAX_WIDTH, MAX_HEIGHT);
        this.setPreferredSize( dimension );
        this.setSize( dimension );
        this.setBackground(Color.GRAY);

        this.setLayout(new BorderLayout());
        this.add(createHeaderPanel(), BorderLayout.NORTH);
    }

    private static JPanel createHeaderPanel(){
        GridBagConstraints constraints = setConstraints();

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new GridBagLayout());

        //UPC
        JPanel upcPanel = createTextPanel(MAX_WIDTH/4, TEXT_HEIGHT, "UPC");
        headerPanel.add(upcPanel, constraints);

        //Item
        constraints.gridx = 1;
        JPanel itemPanel = createTextPanel(MAX_WIDTH/4, TEXT_HEIGHT, "Item");
        headerPanel.add(itemPanel, constraints);

        //QTY
        constraints.gridx = 2;
        JPanel qtyPanel = createTextPanel(MAX_WIDTH/4, TEXT_HEIGHT, "QTY");
        headerPanel.add(qtyPanel, constraints);

        //$
        constraints.gridx = 3;
        JPanel costPanel = createTextPanel(MAX_WIDTH/4, TEXT_HEIGHT, "$");
        headerPanel.add(costPanel, constraints);

        return headerPanel;
    }

    private static GridBagConstraints setConstraints(){
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx =0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.NORTH;
        return constraints;
    }

    private static JPanel createTextPanel(int panelWidth, int panelHeight, String text){
        JPanel newPanel = new JPanel();
        Dimension newDimension = new Dimension(panelWidth, panelHeight);
        newPanel.setPreferredSize( newDimension );
        newPanel.setSize(newDimension);
        newPanel.add( createJLabel(text, FONT_SIZE));

        return newPanel;
    }

    private static JLabel createJLabel(String text, int fontSize){
        JLabel newLabel = new JLabel(text);
        newLabel.setFont(new Font("Calibri", Font.PLAIN , fontSize));
        return newLabel;
    }
}
