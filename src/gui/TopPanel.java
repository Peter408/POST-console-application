package gui;

import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.JPanel;
import java.awt.Label;
import java.awt.TextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class TopPanel extends JPanel {
    static final long serialVersionUID = 1001;
    private TextField customerName = new TextField();

    public TopPanel() {
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
