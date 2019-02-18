package gui.panel.middlepanel.cartitemgui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import gui.productsearch.AddItemFrame;
import gui.productsearch.AddItemPanel;
import item.*;
import store.Catalog;

public class CartItemPanel extends JPanel implements ActionListener {
    static final long serialVersionUID = 20001;
    private static final int MAX_WIDTH = 800;
    private static final int SECTION_WIDTH = MAX_WIDTH / 50;
    private static final String[] COLUMN_NAMES = {
            "UPC",
            "Item",
            "QTY",
            "Unit Price",
            "Total Price",
            "Delete"
    };
    private static final int[] COLUMN_WIDTHS = {
            SECTION_WIDTH * 4,
            SECTION_WIDTH * 20,
            SECTION_WIDTH * 3,
            SECTION_WIDTH * 10,
            SECTION_WIDTH * 10,
            SECTION_WIDTH * 3
    };
    private static final int COLUMN_COUNT = 6;
    private ArrayList<CartItem> cartItem;
    private Catalog catalog;
    private CartItemPanelTableModel tableModel;
    private CartItemPanelTable table;
    private Double totalPrice = 0.0;
    private AddItemPanel.Delegate delegate;

    public CartItemPanel(AddItemPanel.Delegate delegate, Catalog catalog) {
        setDefaultConfiguration(delegate, catalog);
        setComponents();
    }

    public void setDefaultConfiguration(AddItemPanel.Delegate delegate, Catalog catalog) {
        this.setLayout(new BorderLayout());
        this.delegate = delegate;
        this.catalog = catalog;
        this.cartItem = new ArrayList<>();
        tableModel = new CartItemPanelTableModel(new Vector<String>(Arrays.asList(COLUMN_NAMES)), 0);
        table = new CartItemPanelTable(tableModel, this);
        table.setFillsViewportHeight(true);
        for (int i = 0; i < COLUMN_COUNT; i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(COLUMN_WIDTHS[i]);
        }
    }

    public void setComponents() {
        JScrollPane scrollPane = new JScrollPane(table);
        Button addItem = new Button("< Add Item >");
        addItem.addActionListener(this);
        add(addItem, BorderLayout.SOUTH);
        add(scrollPane, BorderLayout.PAGE_START);
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void addItem(CartItem item) {
        cartItem.add(item);
        tableModel.addRow(new Object[]{
                item.getItem().getId(),
                item.getItem().getName(),
                item.getQuantity(),
                item.getItem().getPrice(),
                item.getQuantity() * item.getItem().getPrice(),
                "X"
        });
        table.getColumn("Delete").setCellRenderer(new DeleteRenderer());
        table.getColumn("Delete").setCellEditor(new DeleteEditor(new JCheckBox()));
        totalPrice += item.getQuantity() * item.getItem().getPrice();
    }

    public void removeItem(int index) {
        CartItem item = cartItem.remove(index);
        totalPrice -= item.getQuantity() * item.getItem().getPrice();
    }

    public void clearTable() {
        tableModel.setRowCount(0);
        totalPrice = 0.0;
    }

    public void createAddItemWindow() {
        new AddItemFrame(delegate, catalog);
    }

    public void actionPerformed(ActionEvent action) {
        createAddItemWindow();
    }

    class CartItemPanelTable extends JTable {

        static final long serialVersionUID = 20001;
        private CartItemPanel owner;

        public CartItemPanelTable(CartItemPanelTableModel tableModel, CartItemPanel panel) {
            super(tableModel);
            owner = panel;
        }

        public void removeRow(int row) {
            DefaultCellEditor cellEditor = (DefaultCellEditor)getCellEditor();
            if (null != cellEditor) {
                cellEditor.stopCellEditing();
            }
            ((CartItemPanelTableModel)getModel()).removeRow(row);
            owner.removeItem(row);
        }

    }

    class CartItemPanelTableModel extends DefaultTableModel {

        static final long serialVersionUID = 200002;

        public CartItemPanelTableModel(Vector<String> v, int row) {
            super(v, row);
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return 5 == column;
        }

    }

    class DeleteRenderer extends JButton implements TableCellRenderer {

        static final long serialVersionUID = 20003;
        private JTable table;
        private int row;

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            this.table = table;
            this.row = row;
            setText(value.toString());
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
        protected JButton button;

        public DeleteEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
        }

        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    ((CartItemPanelTable)table).removeRow(row);
                }
            });
            return button;
        }

    }

}
