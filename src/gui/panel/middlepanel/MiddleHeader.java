package gui.panel.middlepanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MiddleHeader {
    private static final int MAX_WIDTH = 800;
    private static final int TEXT_HEIGHT = 30;
    private static final int FONT_SIZE = 14;

    static JPanel createHeaderPanel(){
        GridBagConstraints constraints = setConstraints();
        int sectionWidth = MAX_WIDTH / 50;

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new GridBagLayout());

        //UPC
        JPanel upcPanel = createTextPanel(sectionWidth*4, TEXT_HEIGHT, "UPC");
        headerPanel.add(upcPanel, constraints);

        //Item
        constraints.gridx = 1;
        JPanel itemPanel = createTextPanel(sectionWidth*20, TEXT_HEIGHT, "Item");
        headerPanel.add(itemPanel, constraints);


        //QTY
        constraints.gridx = 2;
        JPanel qtyPanel = createTextPanel(sectionWidth*3, TEXT_HEIGHT, "QTY");
        headerPanel.add(qtyPanel, constraints);

        //$
        constraints.gridx = 3;
        JPanel costPanel = createTextPanel(sectionWidth*10, TEXT_HEIGHT, "Unit Price");
        headerPanel.add(costPanel, constraints);

        //Total
        constraints.gridx = 4;
        JPanel totalPanel = createTextPanel(sectionWidth*10, TEXT_HEIGHT, "Total Price");
        headerPanel.add(totalPanel, constraints);

        //Cancel
        constraints.gridx = 5;
        JPanel cancelSpace = createTextPanel(sectionWidth*3, TEXT_HEIGHT, "");
        headerPanel.add(cancelSpace, constraints);

        return headerPanel;
    }

    private static GridBagConstraints setConstraints(){
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx =0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        return constraints;
    }

    private static JPanel createTextPanel(int panelWidth, int panelHeight, String text){
        JPanel newPanel = new JPanel();
        Dimension newDimension = new Dimension(panelWidth, panelHeight);
        newPanel.setLayout(new BorderLayout());
        newPanel.setPreferredSize( newDimension );
        newPanel.setSize(newDimension);
        newPanel.add( createJLabel(text, FONT_SIZE), BorderLayout.WEST);

        return newPanel;
    }

    private static JLabel createJLabel(String text, int fontSize){
        JLabel newLabel = new JLabel(text);
        newLabel.setFont(new Font("Calibri", Font.PLAIN , fontSize));
        newLabel.setBorder(new EmptyBorder(0,20,0,0));
        return newLabel;
    }
}
