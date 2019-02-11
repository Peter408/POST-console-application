package user;

import item.*;

public class Customer extends User {
    Cart cart;

    public Customer(String name) {
        super(name);
        this.cart = new Cart();
    }

    // TODO return false if not in catalog
    public boolean addToCart(Item item, int quantity) {
        final int itemQuantity = cart.getQuantityForItem(item);
        cart.setQuantityForItem(item, quantity + itemQuantity);
        return true;
    }

    public boolean removeFromCart(Item item, int quantity) {
        final int itemQuantity = cart.getQuantityForItem(item);
        if(itemQuantity == 0 || quantity <= 0) {
            return false;
        }
        final int newQuantity = itemQuantity - quantity;
        if(newQuantity > 0) {
            cart.setQuantityForItem(item, newQuantity);
        } else {
            cart.removeItem(item);
        }
        return true;
    }

    public double getTotal() {
        return this.cart.getTotalCost();
    }

    public Cart getCart() {
        return this.cart;
    }

    public String getCartItemList() {
        return this.cart.displayCartItems();
    }

    @Override
    public String toString() {
        StringBuffer SB = new StringBuffer();
        SB.append(this.getName() + "\n");
        SB.append(this.cart.toString());
        return SB.toString();
    }
}