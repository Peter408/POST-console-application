import java.util.*;
import item.*;
public Class Catalog(){
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




}