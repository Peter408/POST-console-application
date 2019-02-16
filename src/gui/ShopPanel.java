package gui;

import gui.panel.bottompanel.BottomPanel;
import gui.panel.middlepanel.MiddlePanel;
import gui.panel.optionspanel.OptionsPanel;
import gui.panel.toppanel.TopPanel;
import post.POST;

import javax.swing.*;
import java.awt.*;

public class ShopPanel extends JPanel{
    
    private TopPanel topPanel = new TopPanel();
    private MiddlePanel middlePanel;
    private BottomPanel bottomPanel = new BottomPanel();


    public ShopPanel(POST post){
        this.setLayout( new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx =0;
        constraints.gridy = 0;
        constraints.gridheight = 3;

        this.add(new OptionsPanel(), constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridheight = 1;

        this.add(topPanel, constraints);

        constraints.gridy = 1;
        middlePanel = new MiddlePanel(post.getCatalog());
        this.add(middlePanel, constraints);

        constraints.gridy = 2;
        this.add(bottomPanel, constraints);
    }

    public String getCustomerName() {
        return topPanel.getCustomerName();
    }
}

