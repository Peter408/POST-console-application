package post;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import item.Item;
import network.Api;
import store.*;
import transaction.Transaction;
import user.Customer;

/**
 * Public interface to the application
 */
public class POST {

    public final int postid;
    private static int idCount = 0;
    private Store store;
    private Api api;

    public POST(String apiUrl) {
        this.postid = idCount++;
        this.store = new Store(Integer.toString(this.postid));
        try {
            this.api = new Api(apiUrl);
            initializeProductList();
            store.getAvailableItems();
        } catch (MalformedURLException e) {
            System.err.println("Invalid API URL:" + e.getMessage());
        }
    }

    private boolean initializeProductList() {
        try {
            List<Item> products = api.getProducts();
            for (Item item : products) {
                store.addToInventory(item);
                store.addToCatalog(item);
            }
            return true;
        } catch (IOException e) {
            System.err.println("unable to fetch product list:" + e.getMessage());
        }
        return false;
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

    public Catalog getCatalog() {
        return this.store.getCatalog();
    }
}
