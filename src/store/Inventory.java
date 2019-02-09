package store;

import java util.*;
import item.*;


public Class Inventory extends Observerable {
    private HashMap<Item, Integer> inventoryMap; //item keyed to quantity
    private HashMap<String, Item> upcMap;
    
    
    //Add item
    public bool addItem(Item item, int increment = 1){
        Integer quantity = inventoryMap.get(Item);
        if (quantity != null){
            quantity += increment;
            inventoryMap.put(item, quantity);   
            return true;
        }
        return false;
    }

    
    //Remove item based on passed item
    public bool removeItem(Item item, int decrement = 1){
        Integer quantity = inventoryMap.get(Item);
        if (quantity != null){
            quantity -= increment;
            inventoryMap.put(item, quantity);
            setChanged();
            notifyObservers();
            return true;
        }
        return false;   
    }

    //Remove inventory item based on upc / description
    public bool removeItem(String item, int decrement = 1){
        Integer quantity = inventoryMap.get(Item);
        if (quantity != null){
            quantity -= increment;
            inventoryMap.put(item, quantity);
            setChanged();
            notifyObservers();
            return true;
        }
        return false;   
    }

    //Print items in inventory
    public void printInventory (){
        System.out.println(intentoryMap);

    }

    @Override String toString(){
        return inventoryMap.toString();
    }
}