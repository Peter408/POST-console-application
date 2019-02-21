package gui.panel.bottompanel;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Total {
    private JLabel label;
    private JPanel panel;

    void createTotal() {
        JPanel totalPanel = new JPanel();
        totalPanel.setLayout(new BorderLayout());

        label = new JLabel("Total: $0.00");

        totalPanel.add(label, BorderLayout.EAST);

        this.panel = totalPanel;
    }

    JPanel getPanel() {
        return this.panel;
    }

    public void setTotal(String total) {
        label.setText("Total: " + total);
    }
}