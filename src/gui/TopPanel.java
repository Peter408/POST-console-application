package gui;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Label;
import java.awt.TextField;
import javax.swing.JPanel;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TopPanel extends JPanel {
    static final long serialVersionUID = 1001;
    private TextField customerName = new TextField();
    private static final int MAX_WIDTH = 800;
    private static final int MAX_HEIGHT = 75;
    private static final int ITEM_GAP = 5;

    private Dimension dimension;

    public TopPanel() {
        this.dimension = new Dimension(MAX_WIDTH, MAX_HEIGHT);
        this.setPreferredSize(dimension);
        this.setSize(dimension);
        this.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        this.add(new Label("Customer"), gridBagConstraints);
        gridBagConstraints.gridy = 1;
        this.add(customerName, gridBagConstraints);
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 1;
        this.add(new Label((new SimpleDateFormat("MMM dd, yyyy")).format(new Date())), gridBagConstraints);
    }

    public String getCustomerName() {
        return customerName.getText();
    }
}
