package gui.panel.toppanel;

import gui.Resettable;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Label;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TopPanel extends JPanel implements KeyListener, Resettable {
    static final long serialVersionUID = 1001;
    private JTextField customerName = new JTextField();
    private static final int MAX_WIDTH = 800;
    private static final int MAX_HEIGHT = 75;

    private Dimension dimension;
    private JLabel customerLabel;
    private Delegate delegate;

    public interface Delegate {
        void nameChanged(String name);
    }

    public TopPanel(Delegate delegate) {
        this.delegate = delegate;
        setDefaultConfiguration();
        setComponents();
    }

    public String getCustomerName() {
        return customerName.getText();
    }

    private void setDefaultConfiguration() {
        this.dimension = new Dimension(MAX_WIDTH, MAX_HEIGHT);
        this.setPreferredSize(dimension);
        this.setSize(dimension);
        this.setLayout(new BorderLayout());
    }

    private void setComponents() {
        setLeftComponents();
        setRightComponents();
    }

    private void setLeftComponents() {
        JPanel gridBag = new JPanel();
        gridBag.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        customerLabel = new JLabel("Customer");
        gridBag.add(customerLabel, gridBagConstraints);
        gridBagConstraints.gridy = 1;
        customerName.setColumns(20);
        gridBag.add(customerName, gridBagConstraints);
        customerName.addKeyListener(this);
        this.add(gridBag, BorderLayout.WEST);
    }

    private void setRightComponents() {
        JPanel gridBag = new JPanel();
        gridBag.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 0;
        gridBag.add(new Label((new SimpleDateFormat("MMM dd, yyyy")).format(new Date())), gridBagConstraints);
        this.add(gridBag, BorderLayout.EAST);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (this.delegate != null)
            delegate.nameChanged(this.customerName.getText());
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public void reset() {
        this.customerName.setText("");
    }
}
