package gui;

import gui.productsearch.AddItemPanel;
import item.CartItem;
import post.POST;

import javax.swing.*;
import java.util.EventListener;

public class ShopFrame extends JFrame {
    public ShopFrame(POST post) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        this.add( new ShopPanel(post));
        setVisible(true);
        this.pack();
    }
}
