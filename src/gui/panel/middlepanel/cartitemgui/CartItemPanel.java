package gui.panel.middlepanel.cartitemgui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;
import java.util.List;

import gui.Resettable;
import gui.productsearch.AddItemFrame;
import gui.productsearch.AddItemPanel;
import item.*;
import store.Catalog;
import item.Cart;

public class CartItemPanel extends JPanel implements ActionListener, PropertyChangeListener {
    // view constants
    static final long serialVersionUID = 20001;
    private static final int MAX_WIDTH = 800;
    private static final int MAX_HEIGHT = 320;
    private static final int SECTION_WIDTH = MAX_WIDTH / 50;
    private static final String[] COLUMN_NAMES = { "UPC", "Item", "QTY", "Unit Price", "Total Price", "Delete" };
    private static final int[] COLUMN_WIDTHS = { SECTION_WIDTH * 4, SECTION_WIDTH * 20, SECTION_WIDTH * 3,
            SECTION_WIDTH * 10, SECTION_WIDTH * 10, SECTION_WIDTH * 3 };
    private static final int COLUMN_COUNT = 6;
    private Dimension dimension;

    // view data
    private Catalog catalog;
    private CartItemPanelTableModel tableModel;
    private CartItemPanelTable table;
    private Delegate delegate;
    private AddItemPanel.Delegate addItemDelegate;

    public interface Delegate {
        void itemRemovedFromCart(Item item);
    }

    public CartItemPanel(AddItemPanel.Delegate addItemDelegate, Delegate delegate, Catalog catalog, Cart cart) {
        setDefaultConfiguration(addItemDelegate, delegate, catalog, cart);
        setComponents();
    }

    public void setDefaultConfiguration(AddItemPanel.Delegate addItemDelegate, Delegate delegate, Catalog catalog,
            Cart cart) {
        this.setLayout(new BorderLayout());
        this.addItemDelegate = addItemDelegate;
        this.delegate = delegate;
        this.catalog = catalog;
        cart.addPropertyChangeListener(this);
        this.dimension = new Dimension(MAX_WIDTH, MAX_HEIGHT);
        this.setPreferredSize(this.dimension);
        this.setSize(this.dimension);
        tableModel = new CartItemPanelTableModel(new Vector<String>(Arrays.asList(COLUMN_NAMES)), 0);
        table = new CartItemPanelTable(tableModel, this);
        table.setFillsViewportHeight(true);
        for (int i = 0; i < COLUMN_COUNT; i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(COLUMN_WIDTHS[i]);
        }
    }

    public void setComponents() {
        JScrollPane scrollPane = new JScrollPane(table);
        table.setPreferredScrollableViewportSize(this.dimension);
        Button addItem = new Button("< Add Item >");
        addItem.addActionListener(this);
        this.add(addItem, BorderLayout.SOUTH);
        this.add(scrollPane, BorderLayout.PAGE_START);
    }

    private Object[] createTableRow(CartItem item) {
        String unitPrice = String.format("%.2f", item.getUnitPrice());
        String extendedPrice = String.format("%.2f", item.getExtendedPrice());
        return new Object[] { item.getId(), item.getName(), item.getQuantity(), unitPrice, extendedPrice,
                item.getId() };
    }

    public void removeItem(String UPC) {
        ((CartItemPanel.Delegate) this.delegate).itemRemovedFromCart(new Item(UPC));
    }

    public void createAddItemWindow() {
        JFrame frame = AddItemFrame.getInstance(addItemDelegate, catalog);
        frame.setVisible(true);
        frame.toFront();
    }

    public void actionPerformed(ActionEvent action) {
        createAddItemWindow();
    }

    public void propertyChange(PropertyChangeEvent event) {
        tableModel.setRowCount(0);
        List<CartItem> items = ((Cart) event.getSource()).getPurchases();
        Collections.sort(items);
        for (CartItem cartItem : items) {
            tableModel.addRow(this.createTableRow(cartItem));
        }
        table.getColumn("Delete").setCellRenderer(new DeleteRenderer());
        table.getColumn("Delete").setCellEditor(new DeleteEditor(new JCheckBox()));
    }

    class CartItemPanelTable extends JTable {

        static final long serialVersionUID = 20001;
        private CartItemPanel owner;

        public CartItemPanelTable(CartItemPanelTableModel tableModel, CartItemPanel panel) {
            super(tableModel);
            owner = panel;
        }

        public void removeRow(int row, String UPC) {
            DefaultCellEditor cellEditor = (DefaultCellEditor) getCellEditor();
            if (cellEditor != null) {
                cellEditor.stopCellEditing();
            }
            ((CartItemPanelTableModel) getModel()).removeRow(row);
            owner.removeItem(UPC);
        }

    }

    class CartItemPanelTableModel extends DefaultTableModel {

        static final long serialVersionUID = 200002;

        public CartItemPanelTableModel(Vector<String> v, int row) {
            super(v, row);
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return column == 5;
        }

    }

    class DeleteRenderer extends JButton implements TableCellRenderer {

        static final long serialVersionUID = 20003;
        private JTable table;
        private int row;

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {
            this.table = table;
            this.row = row;
            setText("X");
            return this;
        }

        public JTable getTable() {
            return table;
        }

        public int getRow() {
            return row;
        }

    }

    class DeleteEditor extends DefaultCellEditor {

        static final long serialVersionUID = 20004;
        private JButton button;
        private ActionListener actionListener;
        private String UPC;

        public DeleteEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
        }

        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
                int column) {
            UPC = value.toString();
            if (actionListener != null) {
                button.removeActionListener(actionListener);
            }
            actionListener = new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    ((CartItemPanelTable) table).removeRow(row, UPC);
                }
            };
            button.addActionListener(actionListener);

            return button;
        }

    }

}
