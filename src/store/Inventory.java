import java util.*;
import item.*;
package inventory;

public Class Inventory(){
    private HashMap<Item, Integer> inventoryMap; //item keyed to quantity

    //Add item
    public bool addItem(Item item){
        inventoryMap.put(item);
        return true;
    }

    

    //Remove item based on passed item
    public bool removeItem(Item item, int quantity){
        if( inventoryMap.containsValue(item) ) {

        }

    }

    //Remove inventory item based on upc
    public bool removeItem(String item){

    }

    //Print items in inventory
    public void printInventory (){
        System.out.println(intentoryMap);
    }

    @Override String toString(){
        return inventoryMap.toString();
    }
}