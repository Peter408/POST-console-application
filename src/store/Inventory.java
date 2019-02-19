package store;

import item.Item;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;


public class Inventory {
    private PropertyChangeSupport support;
    private HashMap<String, Integer> UPCtoQuantity;

    public Inventory() {
        this.support = new PropertyChangeSupport(this);
        this.UPCtoQuantity = new HashMap<>();
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.support.addPropertyChangeListener(listener);
    }
 
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.support.removePropertyChangeListener(listener);
    }

    public boolean addItem(Item item, int increment) {
        Integer quantity;
        if (null == (quantity = this.UPCtoQuantity.get(item.getId()))) {
            this.UPCtoQuantity.put(item.getId(), increment);
        } else {
            quantity += increment;
            this.UPCtoQuantity.put(item.getId(), increment);
        }
        this.support.firePropertyChange("UPCtoQuantity", null, this.UPCtoQuantity);
        return true;
    }

    public boolean addItem(Item item) {
        return this.addItem(item, 1);
    }

    public boolean removeItem(String UPC, int decrement) {
        Integer quantity = UPCtoQuantity.get(UPC);
        if (quantity != null) {
            if (0 > (quantity -= decrement)) {
                return false; //CANNOT HAVE NEGATIVE ITEM QUANTITY
            }
            UPCtoQuantity.put(UPC, quantity);
        }
        this.support.firePropertyChange("UPCtoQuantity", null, this.UPCtoQuantity);
        return true;
    }

    public boolean removeItem(String item) {
        return this.removeItem(item, 1);
    }
    
    public boolean removeItem(Item item, int decrement) {
        return this.removeItem(item.getId(), decrement);
    }

    public boolean removeItem(Item item) {
        return this.removeItem(item, 1);
    }

    public boolean deleteItem(String UPC) {
        this.UPCtoQuantity.remove(UPC);
        this.support.firePropertyChange("UPCtoQuantity", null, this.UPCtoQuantity);
        return true;
    }

    public boolean deleteItem(Item item) {
        return this.deleteItem(item.getId());
    }

    public Integer getQuantity(String UPC) {
        return this.UPCtoQuantity.get(UPC);
    }

    public HashMap<String, Integer> getInventory() {
        return this.UPCtoQuantity;
    }

    @Override
    public String toString() {
        return "INVENTORY: \n" + UPCtoQuantity.toString() + "\n";
    }
}