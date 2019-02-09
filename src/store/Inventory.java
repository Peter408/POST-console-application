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
            upcMap.put(item.getId, item);
            return true;
        }
        return false;
    }

    
    //Remove item based on passed item
    public bool removeItem(Item item, int decrement = 1){
        Integer quantity = inventoryMap.get(Item);
        if (quantity != null){
            quantity -= increment;

            if(quantity < 0 ) return false; //CANNOT HAVE NEGATIVE ITEMS

            inventoryMap.put(item, quantity);
            return true;
        }
    }

    //Remove inventory item based on upc / description
    public bool removeItem(String item, int decrement = 1){
        Integer quantity = inventoryMap.get(Item);
        if (quantity != null){
            quantity -= increment;

            if(quantity < 0) return false;
            
            inventoryMap.put(item, quantity);
            return true;
        }
    }


    //Remove item completely from inventory
    public bool deleteItem( Item item ) {
        inventoryMap.remove(item);
        upcMap.remove( item.getId() );

        setChanged();
        notifyObservers();
    }

    public bool deleteItem(String upc) {
        inventoryMap.remove(upc);
        upcMap.remove(upc);

        setChanged();
        notifyObservers();
    }

    //Print items in inventory
    public void printInventory (){
        System.out.println(intentoryMap);

    }

    public HashMap getInventory(){
        return inventoryMap;
    }
    @Override String toString(){
        return "INVENTORY: \n" + inventoryMap.toString() + "\n";
    }
}