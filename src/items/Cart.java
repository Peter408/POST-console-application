package items;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a shopping cart when checking out.
 */
public class Cart {
    private List<CartItem> purchases;

    /**
     * Default constructor which initializes purchases to an empty list.
     */
    public Cart() {
        this.purchases = new ArrayList<>();
    }

    /**
     * Standard constructor
     * 
     * @param purchases list of `CartItems` to be purchased
     */
    public Cart(List<CartItem> purchases) {
        this.purchases = purchases;
    }

    /**
     * Accessor for the total cost of all items in a cart.
     * 
     * @return cost in dollars and cents
     */
    public double getTotalCost() {
        return this.purchases.stream().mapToDouble((CartItem i) -> {
            final Item item = i.getItem();
            final double price = item != null ? item.getPrice() : 0;
            return price * i.getQuantity();
        }).sum();
    }

    /**
     * Accessor for items in Cart
     * 
     * @return list of `CartItem`s
     */
    public List<CartItem> getPurchases() {
        return this.purchases;
    }
}