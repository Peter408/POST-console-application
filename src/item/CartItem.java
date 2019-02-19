package item;

/**
 * Represents and item in a cart with a quantity.
 *
 * @author Eric Groom
 */
public class CartItem implements Comparable<CartItem> {
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

    public double getUnitPrice() {
        return this.item.getPrice();
    }

    public double getExtendedPrice() {
        return this.item.getPrice() * this.quantity;
    }

    public String getId() {
        return this.item.getId();
    }

    public String getName() {
        return this.item.getName();
    }

    private void checkQuantity(int quantity) throws IllegalArgumentException {
        if (quantity < 0) {
            throw new IllegalArgumentException("quantity must be greater than or equal to 0");
        }
    }

    /**
     * hashCode implementation, ensure that `CartItem.item` is not null!
     */
    @Override
    public int hashCode() {
        return this.item.hashCode() ^ this.quantity;
    }

    /**
     * Ensure item is not not null. Two `CartItem`s are equal if their item has the
     * same UPC id and they have the same quantity.
     *
     * @return true if objects are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (item == null)
            return false;
        if (obj == null)
            return false;
        if (!(obj instanceof CartItem))
            return false;
        CartItem other = (CartItem) obj;
        if (other.getItem() == null)
            return false;
        return item.equals(other.getItem()) && quantity == other.getQuantity();
    }

    @Override
    public int compareTo(CartItem o) {
        return this.getItem().getId().compareTo(o.getItem().getId());
    }
}