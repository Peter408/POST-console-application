package gui.panel.bottompanel;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;



public class Total {
    String total = "$20";
    JLabel label;
    JPanel panel;

    void createTotal() {
        JPanel totalPanel = new JPanel();
        totalPanel.setLayout(new BorderLayout());
    
        label = new JLabel("Total: $0");

        totalPanel.add(label, BorderLayout.EAST);

        this.panel = totalPanel;
    }

    JPanel getPanel() {
        return this.panel;
    }

    void setTotal(String total) {
        this.total = total;
        label.setText("Total: $" + total);
    }
}