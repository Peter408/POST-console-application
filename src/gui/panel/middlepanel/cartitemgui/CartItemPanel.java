package gui.panel.middlepanel.cartitemgui;

import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import gui.productsearch.AddItemFrame;
import gui.productsearch.AddItemPanel;
import item.*;
import store.Catalog;

public class CartItemPanel extends JPanel {
    static final long serialVersionUID = 20001;
    private static final int MAX_WIDTH = 800;
    private static final int SECTION_WIDTH = MAX_WIDTH / 50;
    ArrayList<CartItem> cartItem;
    Catalog catalog;
    DefaultTableModel defaultTableModel;

    public CartItemPanel(AddItemPanel.Delegate delegate, Catalog catalog) {
        super(new GridLayout(1,0));
        this.catalog = catalog;
        defaultTableModel = new DefaultTableModel(new Vector<String>(
            Arrays.asList(new String[] {
            "UPC",
            "Item",
            "QTY",
            "Unit Price",
            "Total Price",
            "Delete"
            })
        ), 0);

        JTable table = new JTable(defaultTableModel);
        table.setFillsViewportHeight(true);
        table.getColumnModel().getColumn(0).setPreferredWidth(SECTION_WIDTH * 4);
        table.getColumnModel().getColumn(1).setPreferredWidth(SECTION_WIDTH * 20);
        table.getColumnModel().getColumn(2).setPreferredWidth(SECTION_WIDTH * 3);
        table.getColumnModel().getColumn(3).setPreferredWidth(SECTION_WIDTH * 10);
        table.getColumnModel().getColumn(4).setPreferredWidth(SECTION_WIDTH * 10);
        table.getColumnModel().getColumn(5).setPreferredWidth(SECTION_WIDTH * 3);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
    }

    public void addItem(CartItem item) {
        cartItem.add(item);
        defaultTableModel.addRow(new Object[]{
            item.getItem().getId(),
            item.getItem().getName(),
            item.getQuantity(),
            item.getItem().getPrice(),
            item.getQuantity() * item.getItem().getPrice(),
            "X"
        });
    }
}
