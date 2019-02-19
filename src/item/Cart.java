package item;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Cart {
    private PropertyChangeSupport support;
    private Map<Item, Integer> itemToQuantityMap;

    public Cart() {
        this.support = new PropertyChangeSupport(this);
        this.itemToQuantityMap = new HashMap<>();
    }

    public Cart(List<CartItem> purchases) {
        this.setCartItems(purchases);
    }

    public boolean contains(Item item) {
        return this.itemToQuantityMap.containsKey(item);
    }

    public int getQuantityForItem(Item item) {
        return this.itemToQuantityMap.getOrDefault(item, 0);
    }

    public double getTotalCost() {
        return this.itemToQuantityMap.entrySet().stream().mapToDouble(entry -> {
            final Item item = entry.getKey();
            final double price = item != null ? item.getPrice() : 0;
            return price * entry.getValue();
        }).sum();
    }

    public List<CartItem> getPurchases() {
        return this.itemToQuantityMap.entrySet().stream().map(entry -> new CartItem(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.support.addPropertyChangeListener(listener);
    }
 
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.support.removePropertyChangeListener(listener);
    }

    public void setQuantityForItem(Item item, int quantity) throws IllegalArgumentException {
        if (quantity < 0) {
            throw new IllegalArgumentException("quantity must be greater than or equal to 0");
        }
        this.itemToQuantityMap.put(item, quantity);
        this.support.firePropertyChange("itemToQuantityMap", null, itemToQuantityMap);
    }

    public int getQuantityForItem(Item item) {
        return this.itemToQuantityMap.getOrDefault(item, 0);
    }

    public int removeItem(Item item) {
        int returnValue = this.itemToQuantityMap.remove(item);
        this.support.firePropertyChange("itemToQuantityMap", null, itemToQuantityMap);
        return returnValue;
    }

    public void clearCart() {
        this.itemToQuantityMap.clear();
        this.support.firePropertyChange("itemToQuantityMap", null, itemToQuantityMap);
    }

    public void setCartItems(List<CartItem> items) {
        this.itemToQuantityMap = items.stream().filter(i -> i != null && i.getItem() != null)
                .collect(Collectors.toMap(item -> item.getItem(), item -> item.getQuantity()));
        this.support.firePropertyChange("itemToQuantityMap", null, itemToQuantityMap);
    }

    public void add(CartItem cartItem) {
        if (null != this.itemToQuantityMap.get(cartItem.getItem())) {
            this.setQuantityForItem(cartItem.getItem(), this.getQuantityForItem(cartItem.getItem()) + cartItem.getQuantity());
        } else {
            this.setQuantityForItem(cartItem.getItem(), cartItem.getQuantity());
        }
        this.support.firePropertyChange("itemToQuantityMap", null, itemToQuantityMap);
    }

    @Override
    public String toString() {
        StringBuffer SB = new StringBuffer();
        this.itemToQuantityMap.forEach((k, v) -> {
            SB.append("Item: " + k.getName() + " " + v + " @ $" + String.format("%.2f", k.getPrice()) + " $"
                    + String.format("%.2f", k.getPrice() * v) + "\n");
        });
        return SB.toString();
    }
}