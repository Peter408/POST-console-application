package user;

import items.Cart;

public class Customer extends User {
    Cart cart;

    public Customer(string name) {
        super(name);
        this.cart = new Cart();
    }

    // TODO: what if item is in cart?
    public boolean addToCart(Item item, int quantity) {
        try {
            cart.setQuantityForItem(item, quantity);
            return true;
        } catch(Exception exception) {
            return false;
        }
        
    }

    // TODO: do something with qanitity
    public boolean removeFromCart(Item item, int quantity) {
        try {
            cart.removeItem(item);
            return true;
        } catch(Exception exception) {
            return false;
        }
    }

    public double getTotal() {
        return this.cart.getTotalCost();
    }
}