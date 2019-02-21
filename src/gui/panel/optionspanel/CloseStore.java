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
import javax.swing.Action;

public class CloseStore {
    JButton button;
    JPanel panel;
    private static final int WIDTH = 35;
    private static final int HEIGHT = 35;
    private Dimension dimension;

    Delegate delegate;

    public interface Delegate {
        public void closeButtonClicked();
    }

    public CloseStore(Delegate delegate) {
        this.delegate = delegate;
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
            Image img = ImageIO.read(getClass().getResource("/gui/resources/offButton.png")).getScaledInstance(WIDTH,HEIGHT, Image.SCALE_DEFAULT);
            button.setIcon(new ImageIcon(img));
            button.setBorder(BorderFactory.createEmptyBorder());
        } catch(Exception e) {
            System.out.println("No image found");
        }

        closeStorePanel.add(button, BorderLayout.CENTER);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (delegate != null)
                    delegate.closeButtonClicked();
            }
        });

        this.panel = closeStorePanel;
    }

    JPanel getPanel() {
        return this.panel;
    }
}