package store;

import item.Item;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;

public class Catalog implements PropertyChangeListener {
    private List<Item> catalogItems;

    public Catalog() {
        catalogItems = new ArrayList<>();
    }

    public boolean addToCatalog(Item item) {
        if (this.getItem(item) == null) {
            catalogItems.add(item);
        }
        return true;
    }

    public boolean removeFromCatalog(Item item) {
        return catalogItems.remove(item);
    }

    public List<Item> getAvailableItems() {
        return this.catalogItems;
    }

    public Item getItem(Item item) {
        Item result = null;
        int index = this.catalogItems.indexOf(item);
        if (index != -1) {
            result = this.catalogItems.get(index);
        }
        return result;
    }

    public ArrayList<Item> searchItemByName(String query){
        ArrayList<Item> resultsList = new ArrayList<>();

        for( Item item : catalogItems){
            if(item.getName().toLowerCase().contains(query.toLowerCase())){
                resultsList.add(item);
            }
        }
        return resultsList;
    }

    public ArrayList<Item> searchItemByUPC(String upc){
        ArrayList<Item> resultsList = new ArrayList<>();
        for(Item item : catalogItems){
            if(item.getId().equalsIgnoreCase(upc)){
                resultsList.add(item);
            }
        }
        return resultsList;
    }

    public Item getItem(String searchParameter) {
        for (Item item : catalogItems) {
            if (item.getId().equalsIgnoreCase(searchParameter) || item.getName().equalsIgnoreCase(searchParameter)) {
                return item;
            }
        }
        return null;
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        HashMap<String, Integer> inventoryUpdate = ((Inventory)event.getSource()).getInventory();
        for (Item item : catalogItems) {
            if (!inventoryUpdate.containsKey(item.getId())) {
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