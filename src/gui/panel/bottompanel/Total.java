package gui.panel.bottompanel;

import java.awt.*;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Total {
    private JLabel label;
    private JPanel panel;

    public Total() {
        createTotal();
    }

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
        label.setForeground(Color.BLACK);
        label.setText("Total: " + total);
    }

    public void setChange(String change) {
        label.setForeground(Color.RED);
        label.setText("Change: " + change);
    }
}