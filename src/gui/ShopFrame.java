package gui;
import javax.swing.*;
import java.awt.*;
import java.util.EventListener;

public class ShopFrame extends JFrame implements EventListener {
    public ShopFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        this.add( new ShopPanel());
        setVisible(true);
        this.pack();
    }
}
