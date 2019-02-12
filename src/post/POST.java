package post;

import item.Item;
import store.Store;
import transaction.Transaction;
import user.Customer;

/**
 * Public interface to the application
 */
public class POST {

    public final int postid;
    private static int idCount = 0;
    private Store store;

    public POST() {
        this.postid = idCount++;
        this.store = new Store(Integer.toString(this.postid));
    }

    /**
     * Completes a transaction.
     *
     * @param customer    user purchasing item(s)
     * @param paymentType String of "CREDIT", "CASH", or "CHECK"
     * @param cardNumber  String of card number
     * @return Transaction instance
     */
    public Transaction checkout(Customer customer, String paymentType, String cardNumber) {
        return new Transaction(customer, paymentType, cardNumber);
    }

    public boolean addItemToCatalog(Item item) {
        return this.store.addToCatalog(item);
    }

    public boolean removeItemFromCatalog(Item item) {
        return this.store.removeFromCatalog(item);
    }

    public boolean addItemToInventory(Item item) {
        return this.store.addToInventory(item);
    }

    public boolean removeItemFromInventory(Item item) {
        return this.store.removeFromInventory(item);
    }

    public boolean openStore() {
        return this.store.open();
    }

    public boolean closeStore() {
        return this.store.close();
    }

    public Store getStore() {
        return this.store;
    }
}
