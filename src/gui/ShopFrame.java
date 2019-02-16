package gui;

import post.POST;

import javax.swing.*;
import java.util.EventListener;

public class ShopFrame extends JFrame implements EventListener {
    public ShopFrame(POST post) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        this.add( new ShopPanel(post));
        setVisible(true);
        this.pack();
    }
}
