package post;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import item.Item;
import network.Api;
import store.*;
import transaction.Invoice;
import transaction.Payment;
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
    private List<Transaction> transactions;

    public POST(String apiUrl) {
        this.postid = idCount++;
        this.store = new Store(Integer.toString(this.postid));
        this.transactions = new ArrayList<>();
        try {
            this.api = new Api(apiUrl);
        } catch (MalformedURLException e) {
            System.err.println("Invalid API URL:" + e.getMessage());
        }
    }

    public boolean initializeProductList() {
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

    public boolean validatePayment(Payment payment) {
        if ( payment.getChange() < 0 ) { return false; }
        try {
            return this.api.putPaymentType(payment);
        } catch (IOException e) {
            return false;
        }
    }

    public Transaction checkout(Customer customer, Payment payment) {
        Customer savedCustomer = new Customer(customer);
        Transaction t = new Transaction(savedCustomer, payment);
        transactions.add(t);
        try {
            api.putTransaction(t);
        } catch (IOException e) {
            System.err.println("Unable to PUT transaction to api: " + e.getMessage());
        }
        return t;
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
        for (Transaction transaction : transactions) {
            Invoice invoice = new Invoice(transaction);
            System.out.println(invoice.displayInvoice());
        }
        this.transactions.clear();
        return this.store.close();
    }

    public Store getStore() {
        return this.store;
    }

    public Catalog getCatalog() {
        return this.store.getCatalog();
    }
}
