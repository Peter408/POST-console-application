package items;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a shopping cart when checking out.
 */
public class Cart {
    private Map<Item, Integer> itemToQuantityMap;

    /**
     * Default constructor which initializes purchases to an empty list.
     */
    public Cart() {
        this.itemToQuantityMap = new HashMap<>();
    }

    /**
     * Standard constructor.
     * 
     * @param purchases list of `CartItems` to be purchased. Null values and
     *                  `CartItem`s with null `Item`s will be ignored.
     */
    public Cart(List<CartItem> purchases) {
        this.itemToQuantityMap = purchases.stream().filter(i -> i != null && i.getItem() != null)
                .collect(Collectors.toMap(item -> item.getItem(), item -> item.getQuantity()));
    }

    /**
     * Accessor for the total cost of all items in a cart.
     * 
     * @return cost in dollars and cents
     */
    public double getTotalCost() {
        return this.itemToQuantityMap.entrySet().stream().mapToDouble(entry -> {
            final Item item = entry.getKey();
            final double price = item != null ? item.getPrice() : 0;
            return price * entry.getValue();
        }).sum();
    }

    /**
     * Accessor for items in Cart
     * 
     * @return list of `CartItem`s
     */
    public List<CartItem> getPurchases() {
        return this.itemToQuantityMap.entrySet().stream().map(entry -> new CartItem(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    /**
     * Sets the quantity for a certain item. If item doesn't exists in cart it will
     * be added.
     * 
     * @param item     `Item` instance
     * @param quantity quantity of item
     * @throws IllegalArgumentException if quantity is less than 0
     */
    public void setQuantityForItem(Item item, int quantity) throws IllegalArgumentException {
        if (quantity < 0) {
            throw new IllegalArgumentException("quantity must be greater than or equal to 0");
        }
        this.itemToQuantityMap.put(item, quantity);
    }

    /**
     * Gets the quantity of a certain item in a cart
     * 
     * @param item `Item` instance
     * @return quantity of item in cart or 0 if not exists
     */
    public int getQuantityForItem(Item item) {
        return this.itemToQuantityMap.getOrDefault(item, 0);
    }

    /**
     * Removes all of a certain item from the cart. This is not to deduct from the
     * quantity of the item but remove it entirely, instead use
     * `setQuantityForItem`.
     * 
     * @param item `Item` instace
     * @return the quantity of this item that was contained in the cart
     */
    public int removeItem(Item item) {
        return this.itemToQuantityMap.remove(item);
    }
}