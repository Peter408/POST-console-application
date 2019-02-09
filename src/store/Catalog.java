package store;

import java.util.*;
import item.*;

public Class Catalog implements Observer{
    private List<Item> catalogItems;

    //Add catalog item
    public bool addToCatalog (Item item) {
        //check if in Inventory first
        catalogItems.add(item);
        return true;
    }

    //Remove catalog item based on item
    public bool removeFromCatalog (Item item) {
        return catalogItems.remove(item);
    }


    //Retrieve all items in catalog
    public List<Items> getAvailableItems () {
        return this.catalogItems;
    }

    //Retrieve single item in catalog based on item
    public Item searchItem( Item item) {
        Item result;
        int index = this.catalogItems.indexOf(item);
        if(index != -1) {
            result = this.catalogItems.get(index);
        }
        return result;
    }

    //Retrieve items in catalog based on upc / description
    //Should check inventory
    public Item searchItem (String searchParameter){
        return null;
    }

    //Print all items in catalog
    public void printCatalog() {
        System.out.println(this.catalog);
    }


    //if item removed from inventory remove it from catalog
    @Override
    public void update(Observable observable, Object arg)
    {
            inventoryUpdate = (Inventory) observable;
            inventory = inventoryUpdate.getInventory();
            for (Item item : catalogItems){ 
                if( !inventory.containsKey(item) ){
                    catalogItems.remove(item);
                    return;
                } 
            }
    }

    @Override
    public String toString(){
        return "CATALOG: \n" + this.catalogItems.toString() + "\n";
    }



}