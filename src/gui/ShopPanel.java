package gui;

import javax.swing.*;
import java.awt.*;

public class ShopPanel extends JPanel{
    private TopPanel topPanel = new TopPanel();
    public ShopPanel(){
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
        this.add(new MiddlePanel(), constraints);

        constraints.gridy = 2;
        this.add(new BottomPanel(), constraints);
    }

    public String getCustomerName() {
        return topPanel.getCustomerName();
    }
}

