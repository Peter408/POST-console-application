package user;

public class Customer extends User {
    Cart cart;

    public Customer(string name) {
        super(name);
        this.cart = new Cart();  // add params
    }

    // TODO fill when cart is done
    public boolean addToCart(Item item, int quantity) {
        return false;
    }

    // TODO fill when cart is done
    public boolean removeFromCart(Item item, int quantity) {
        return false;
    }
}