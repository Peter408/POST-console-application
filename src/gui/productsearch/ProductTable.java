package gui.productsearch;

import item.Item;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ProductTable extends JPanel {

    static final long serialVersionUID = 60001;
    private static final int WIDTH = 600;
    private static final int HEIGHT = 300;
    private static final int SECTION_WIDTH = WIDTH / 34;
    private static final int COLUMN_COUNT = 3;
    private static final String[] COLUMN_NAMES = { "UPC", "Item", "Unit Price" };
    private static final int[] COLUMN_WIDTHS = { SECTION_WIDTH * 4, SECTION_WIDTH * 20, SECTION_WIDTH * 10 };
    private Dimension dimension;
    private DefaultTableModel tableModel;
    private JTable table;
    private List<Item> data;

    public ProductTable() {
        setDefaultConfiguration();
        setComponents();
    }

    private void setDefaultConfiguration() {
        this.dimension = new Dimension(WIDTH, HEIGHT);
        this.setPreferredSize(dimension);
        this.setSize(dimension);
        this.setBackground(Color.CYAN);
        this.setLayout(new GridLayout(1, 1));
        this.data = new ArrayList<>();
        tableModel = new ProductTableModel(new Vector<String>(Arrays.asList(COLUMN_NAMES)), 0);
        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        for (int i = 0; i < COLUMN_COUNT; i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(COLUMN_WIDTHS[i]);
        }
    }

    public void setComponents() {
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
    }

    public void setData(List<Item> data) {
        this.data = data;
        tableModel.setRowCount(0);
        for (Item item : data) {
            tableModel.addRow(new Object[] { item.getId(), item.getName(), item.getPrice() });
        }
    }

    public Item getSelectedItem() {
        int index = table.getSelectedRow();
        if (-1 != index) {
            return data.get(index);
        } else {
            return null;
        }
    }

    class ProductTableModel extends DefaultTableModel {

        static final long serialVersionUID = 60001;

        public ProductTableModel(Vector<String> v, int row) {
            super(v, row);
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }

    }

}
