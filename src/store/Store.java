package store;

import item.Item;

import java.util.List;

public class Store {

    private String storeName;
    private Inventory inventory;
    private Catalog catalog;
    private boolean isOpen = false;

    public void initializeStore() {
        this.inventory = new Inventory();
        this.catalog = new Catalog();
        this.inventory.addPropertyChangeListener(catalog);
    }

    public Store(String storeName) {
        initializeStore();
        this.storeName = storeName;
    }

    public boolean open() {
        this.isOpen = true;
        return isOpen;
    }

    public boolean close() {
        this.isOpen = false;
        return isOpen;
    }

    public boolean addToInventory(Item item, int quantity) {
        return this.inventory.addItem(item, quantity);
    }

    public boolean addToInventory(Item item) {
        return this.inventory.addItem(item);
    }

    public boolean removeFromInventory(Item item) {
        return this.inventory.removeItem(item);
    }

    public boolean removeFromInventory(Item item, int quantity) {
        return this.inventory.removeItem(item, quantity);
    }

    public boolean removeFromInventory(String upc) {
        return this.inventory.removeItem(upc);
    }

    public boolean removeFromInventory(String upc, int quantity) {
        return this.inventory.removeItem(upc, quantity);
    }

    public void printInventory() {
        System.out.println(this.inventory);
    }


    public boolean addToCatalog(Item item) {
        if (this.inventory.getQuantity(item.getId()) != null) {
            return this.catalog.addToCatalog(item);
        } else return false;
    }

    public boolean removeFromCatalog(Item item) {
        return this.catalog.removeFromCatalog(item);
    }

    public Catalog getCatalog() {
        return this.catalog;
    }

    public List<Item> getAvailableItems() {
        return this.catalog.getAvailableItems();
    }

    public Item searchItem(Item item) {
        return this.catalog.getItem(item);
    }

    public Item searchItem(String searchParameter) {
        return this.catalog.getItem(searchParameter);
    }

    public void printCatalog() {
        System.out.println(this.catalog);
    }

    public boolean isOpen() {
        return this.isOpen;
    }

    @Override
    public String toString() {
        return storeName + "\n" + this.catalog.toString() + this.inventory.toString();
    }

}