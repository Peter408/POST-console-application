package store;

import item.Item;

import java.util.*;

public class Catalog implements Observer {
    private List<Item> catalogItems;

    public Catalog() {
        catalogItems = new ArrayList<>();
    }

    //Add catalog item
    public boolean addToCatalog(Item item) {
        if (this.getItem(item) == null) {
            catalogItems.add(item);
        }
        return true;
    }

    //Remove catalog item based on item
    public boolean removeFromCatalog(Item item) {
        return catalogItems.remove(item);
    }


    //Retrieve all items in catalog
    public List<Item> getAvailableItems() {
        return this.catalogItems;
    }

    //Retrieve single item in catalog based on item
    public Item getItem(Item item) {
        Item result = null;
        int index = this.catalogItems.indexOf(item);
        if (index != -1) {
            result = this.catalogItems.get(index);
        }
        return result;
    }

    //Retrieve items in catalog based on upc / description
    public Item getItem(String searchParameter) {
        for (Item item : catalogItems) {
            if (item.getId().equalsIgnoreCase(searchParameter) || item.getName().equalsIgnoreCase(searchParameter)) {
                return item;
            }
        }
        return null;
    }


    //if item removed from inventory remove it from catalog
    @Override
    public void update(Observable observable, Object arg) {
        Inventory inventoryUpdate = (Inventory) observable;
        HashMap inventory = inventoryUpdate.getInventory();
        for (Item item : catalogItems) {
            if (!inventory.containsKey(item)) {
                catalogItems.remove(item);
                return;
            }
        }
    }

    @Override
    public String toString() {
        return "CATALOG: \n" + this.catalogItems.toString() + "\n";
    }
}