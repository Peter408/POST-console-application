package items;

/**
 * Represents and item in a cart with a quantity.
 * 
 * @author Eric Groom
 */
public class CartItem {
    /**
     * No setter for item, if you are setting the item you should be creating a new
     * instance instead
     */
    private Item item;
    private int quantity;

    /**
     * Default constructor that sets `item` to null and `quantity` to 0.
     */
    public CartItem() {
        this.item = null;
        this.quantity = 0;
    }

    /**
     * Standard constructor for CartItem
     * 
     * @param item     item to be stored in a cart
     * @param quantity number of `Item`s to store, must be greater than or equal to
     *                 0
     * @throws IllegalArgumentException if quantity < 0
     */
    public CartItem(Item item, int quantity) throws IllegalArgumentException {
        checkQuantity(quantity);
        this.item = item;
        this.quantity = quantity;
    }

    /**
     * Item accessor
     * 
     * @return `Item` instance that belongs to this class
     */
    public Item getItem() {
        return this.item;
    }

    /**
     * Quantity accessor
     * 
     * @return quantity of CartItem
     */
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * Mutator of quantity of CartItem.
     * 
     * @param quantity new quantity of CartItem, must be greater than or equal to 0.
     * @throws IllegalArgumentException if quantity < 0
     */
    public void setQuantity(int quantity) throws IllegalArgumentException {
        checkQuantity(quantity);
        this.quantity = quantity;
    }

    private void checkQuantity(int quantity) throws IllegalArgumentException {
        if (quantity < 0) {
            throw new IllegalArgumentException("quantity must be greater than or equal to 0");
        }
    }
}