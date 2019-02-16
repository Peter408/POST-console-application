/**********************************************************************
 * Store class is responsible for storing and manipulating items
 *  in stock / Catalog
 **********************************************************************/
package store;

import item.Item;

import java.util.List;

public class Store {
    private String storeName;
    private Inventory inventory;
    private Catalog catalog;
    private boolean isOpen = false;

    /**********************************************
     * Constructors
     **********************************************/

    public void initializeStore() {
        this.inventory = new Inventory();
        this.catalog = new Catalog();
        this.inventory.addObserver(this.catalog); //catalog may need to update if inventory item is removed
    }

    //Constructor for empty store, initializes empty inventory and catalog
    //Private -- will only be called within this store class, should never be called outside
    private Store() {
        initializeStore();
    }

    //Creates a store with the given name, and an empty inventory / catalog
    public Store(String storeName) {
        initializeStore();
        this.storeName = storeName;
    }

    /**********************************************
     * Store Open / Close
     **********************************************/
    public boolean open() {
        this.isOpen = true;
        return isOpen;
    }

    public boolean close() {
        this.isOpen = false;
        return isOpen;
    }

    /**********************************************
     * Inventory Operations
     **********************************************/

    //Add item to inventory
    public boolean addToInventory(Item item, int quantity) {
        return this.inventory.addItem(item, quantity);
    }

    public boolean addToInventory(Item item) {
        return this.inventory.addItem(item);
    }

    //Remove item based on passed item
    public boolean removeFromInventory(Item item) {
        return this.inventory.removeItem(item);
    }

    public boolean removeFromInventory(Item item, int quantity) {
        return this.inventory.removeItem(item, quantity);
    }

    //Remove inventory item based on upc
    public boolean removeFromInventory(String upc) {
        return this.inventory.removeItem(upc);
    }

    public boolean removeFromInventory(String upc, int quantity) {
        return this.inventory.removeItem(upc, quantity);
    }

    //Print items in inventory
    public void printInventory() {
        System.out.println(this.inventory);
    }


    /**********************************************
     * Catalog Operations
     **********************************************/

    //Add catalog item
    public boolean addToCatalog(Item item) {
        if (this.inventory.getItem(item) != null) {
            return this.catalog.addToCatalog(item);
        } else return false;
    }

    //Remove catalog item based on item
    public boolean removeFromCatalog(Item item) {
        return this.catalog.removeFromCatalog(item);
    }

    //Get catalog object
    public Catalog getCatalog() {
        return this.catalog;
    }


    //Retrieve all items in catalog
    public List<Item> getAvailableItems() {
        return this.catalog.getAvailableItems();
    }

    //Retrieve single item in catalog based on item
    public Item searchItem(Item item) {
        return this.catalog.getItem(item);
    }

    //Retrieve items in catalog based on upc / description
    public Item searchItem(String searchParameter) {
        return this.catalog.getItem(searchParameter);
    }

    //Print all items in catalog
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