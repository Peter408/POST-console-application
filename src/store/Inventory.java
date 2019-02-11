package store;

import java.util.*;
import item.*;


public class Inventory extends Observable {
    private HashMap<Item, Integer> inventoryMap; //item keyed to quantity
    private HashMap<String, Item> upcMap;

    public Inventory(){
        this.inventoryMap = new HashMap<>();
        this.upcMap = new HashMap<>();
    }
    
    //Add item
    public boolean addItem(Item item, int increment){
        Integer quantity = null;
        if (inventoryMap.containsKey(item)) {
            quantity = inventoryMap.get(item);
        }

        if (quantity == null) {
            quantity = 0;
        }

        quantity += increment;
        inventoryMap.put(item, quantity);
        upcMap.put(item.getId(), item);
        return true;
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

    public Item getItem(String upc){
        return this.upcMap.get(upc);
    };

    public Item getItem(Item item){
        return this.upcMap.get(item.getId());
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