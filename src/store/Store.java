/**********************************************************************
* Store class is responsible for storing and manipulating items 
*  in stock / Catalog
**********************************************************************/
package store;

import java.util.*;

public Class Store(){
    private String storeName;
    private Inventory inventory;
    private Catalog catalog;
    private List <POST> terminals;
    private boolean isOpen = false;

    /**********************************************
    * Constructors
    **********************************************/

    //Constructor for empty store, initializes empty inventory and catalog
    //Private -- will only be called within this store class, should never be called outside
    private Store(){
        this.inventory = new Inventory();
        this.catalog = new Catalog();
        this.inventory.addObservor(this.catalog); //catalog may need to update if inventory item is removed
    }

    //Creates a store with the given name, and an empty inventory / catalog
    public Store(String storeName){
        Store();
        this.storeName = storeName;
    }

    //Create store and assign initial POST
    public Store (String storeName, POST post){
        Store(storeName);
        terminals.add(post);
    }

    /**********************************************
    * Store Open / Close
    **********************************************/
    public bool open(){
        this.isOpen = true;
        return isOpen;
    }

    public bool close(){
        this.isOpen = false;
        return isOpen;
    }
    
    /**********************************************
    * Inventory Operations
    **********************************************/

    //Add item to inventory
    public bool addToInventory (Item item){ 
        return this.inventory.addItem(item);
    }

    //Remove item based on passed item
    public bool removeFromInventory (Item item){
        return this.inventory.removeItem(item) 
    }

    //Remove inventory item based on upc
    public bool removeFromInventory (String upc){
        return this.inventory.removeItem(String upc);
    }

    //Print items in inventory
    public void printInventory (){
        System.out.println(this.inventory);
    }


    /**********************************************
    * Catalog Operations
    **********************************************/

    //Add catalog item
    public bool addToCatalog (Item item) {
        return this.catlog.addItem(item);
    }

    //Remove catalog item based on item
    public bool removeFromCatalog (Item item) {
        return this.catalog.removeItem(item);
    }


    //Retrieve all items in catalog
    public List<Items> getAvailableItems () {
        return this.catalog.getAvailableItems();
    }

    //Retrieve single item in catalog based on item
    public Item searchItem( Item item) {
        return this.catalog.searchItem(item);
    }

    //Retrieve items in catalog based on upc / description
    public Item searchItem (String searchParameter){
        return this.catalog.searchItem(String searchParameter);
    }

    //Print all items in catalog
    public void printCatalog() {
        System.out.println(this.catalog);
    }

    @Override String toString() {
        return storeName + "\n" + this.catalog.toString() + this.inventory.toString();
    }

}