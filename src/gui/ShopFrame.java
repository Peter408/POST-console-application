package gui;
import javax.swing.*;
import java.awt.*;
import java.util.EventListener;

public class ShopFrame extends JFrame implements EventListener {
    public ShopFrame() {
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setResizable( false );
        //this.add( new TopPanel() );
        //this.add( new MiddlePanel() );
        //this.add( new BottomPanel() );
        setVisible( true );
        this.pack();
    }
}
