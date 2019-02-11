package item;

/**
 * Represents an item in a catalog
 * 
 * @author Eric Groom
 */
public class Item {
    private String id;
    private String name;
    /**
     * Consider replacing with Math.BigDecimal
     */
    private double price;

    /**
     * Default constructor, sets `id` and `name` to null and `price` to 0.0
     */
    public Item() {
        this.id = null;
        this.name = null;
        this.price = 0;
    }

    /**
     * Standard-use constructor
     * 
     * @param id    UPC id
     * @param name  Name/description of product
     * @param price Price in dollars and cents
     */
    public Item(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    /**
     * Accessor for UPC id
     * 
     * @return UPC id as String
     */
    public String getId() {
        return this.id;
    }

    /**
     * Accessor for item name
     * 
     * @return item's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Accessor for item's price
     * 
     * @return item's price in dollars and cents
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Mutator for item's UPC id
     * 
     * @param id new UPC id as String
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Mutator for item's name
     * 
     * @param name new item name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Mutator for item's price
     * 
     * @param price new price in dollars and cents
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * implements hashCode, note that this expects UPC id's to be unique, if two
     * items have the same id they are seen as equal.
     */
    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    /**
     * implements equals, note that this expects UPC id's to be unique, if two items
     * have the same id they are seen as equal.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof Item))
            return false;
        final Item asItem = (Item) obj;
        if (asItem.id == null)
            return false;
        return this.id.equals(asItem.id);
    }

    @Override
    public String toString() {
        return String.format("id: %10s name: %20s price: %.2f", this.id, this.name, this.price);
    }
}