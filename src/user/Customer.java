package user;

import item.Cart;
import item.CartItem;
import item.Item;

public class Customer extends User {
    private Cart cart;

    public Customer(String name) {
        super(name);
        this.cart = new Cart();
    }

    public boolean addToCart(Item item, int quantity) {
        final int itemQuantity = cart.getQuantityForItem(item);
        cart.setQuantityForItem(item, quantity + itemQuantity);
        return true;
    }

    public boolean addToCart( CartItem item){
        addToCart(item.getItem(), item.getQuantity());
        return true;
    }

    public boolean removeFromCart(Item item) {
        this.cart.removeItem(item);
        return true;
    }

    public boolean removeFromCart(Item item, int quantity) {
        final int itemQuantity = cart.getQuantityForItem(item);
        if (itemQuantity == 0 || quantity <= 0) {
            return false;
        }
        final int newQuantity = itemQuantity - quantity;
        if (newQuantity > 0) {
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
        return this.cart.toString();
    }

    @Override
    public String toString() {
        StringBuffer SB = new StringBuffer();
        SB.append(this.getName() + "\n");
        SB.append(this.cart.toString());
        return SB.toString();
    }
}