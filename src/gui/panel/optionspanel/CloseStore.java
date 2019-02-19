package gui.panel.optionspanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.BorderFactory;

public class CloseStore {
    JButton button;
    JPanel panel;
    private static final int WIDTH = 35;
    private static final int HEIGHT = 35;
    private Dimension dimension;

    public CloseStore() {
        createCloseStore();
    }

    void createCloseStore() {
        JPanel closeStorePanel = new JPanel();
        dimension = new Dimension(WIDTH, HEIGHT);

        closeStorePanel.setLayout(new BorderLayout());
        closeStorePanel.setPreferredSize(dimension);
        closeStorePanel.setSize(dimension);

        button = new JButton();
        try {
            // TODO fix route to button to reource dir
            Image img = ImageIO.read(getClass().getResource("offButton.png")).getScaledInstance(WIDTH,HEIGHT, Image.SCALE_DEFAULT);
            button.setIcon(new ImageIcon(img));
            button.setBorder(BorderFactory.createEmptyBorder());
        } catch(Exception e) {
            System.out.println("Broken");
        }

        closeStorePanel.add(button, BorderLayout.CENTER);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("close store");
            }
        });

        this.panel = closeStorePanel;
    }

    JPanel getPanel() {
        return this.panel;
    }
}