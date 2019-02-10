package store;

import java.util.*;
import items.*;


public class Inventory extends Observable {
    private HashMap<Item, Integer> inventoryMap; //item keyed to quantity
    private HashMap<String, Item> upcMap;
    
    
    //Add item
    public boolean addItem(Item item, int increment){
        Integer quantity = inventoryMap.get(item);
        if (quantity != null){
            quantity += increment;
            inventoryMap.put(item, quantity);   
            upcMap.put(item.getId(), item);
            return true;
        }
        return false;
    }

    public boolean addItem(Item item){
        return addItem(item, 1);
    }

    
    //Remove item based on passed item
    public boolean removeItem(Item item, int decrement){
        Integer quantity = inventoryMap.get(item);
        if (quantity != null){
            quantity -= decrement;

            if(quantity < 0 ) return false; //CANNOT HAVE NEGATIVE ITEMS

            inventoryMap.put(item, quantity);
        }
        return true;
    }

    public boolean removeItem(Item item){
        return removeItem(item, 1);
    }


    //Remove inventory item based on upc / description
    public boolean removeItem(String item, int decrement){
        Integer quantity = inventoryMap.get(item);
        if (quantity != null){
            quantity -= decrement;

            if(quantity < 0) return false;

            Item nameOnly = new Item();
            nameOnly.setId(item);
            inventoryMap.put(nameOnly, quantity);
        }
        return true;
    }

    public boolean removeItem(String item){
        return removeItem(item, 1);
    }


    //Remove item completely from inventory
    public boolean deleteItem( Item item ) {
        inventoryMap.remove(item);
        upcMap.remove( item.getId() );

        setChanged();
        notifyObservers();
        return true;
    }

    public boolean deleteItem(String upc) {
        inventoryMap.remove(upc);
        upcMap.remove(upc);

        setChanged();
        notifyObservers();
        return true;
    }

    //Print items in inventory
    public void printInventory (){
        System.out.println(inventoryMap);
    }

    public HashMap getInventory(){
        return inventoryMap;
    }
    @Override public String toString(){
        return "INVENTORY: \n" + inventoryMap.toString() + "\n";
    }
}