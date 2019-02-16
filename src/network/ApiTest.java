package network;

import java.io.IOException;
import java.net.MalformedURLException;
import item.Item;
import transaction.Transaction;
import user.Customer;

class ApiTest {
    public static void main(String[] args) {
        try {
            Api api = new Api("https://post-server.herokuapp.com");
            Customer c = new Customer("Eric");
            Transaction t = new Transaction(c, "CREDIT", "12312");
            System.out.println(api.putTransaction(t));
            // for (Item item : api.getProducts()) {
            // System.out.println(item);
            // }
        } catch (MalformedURLException e) {
            System.out.println("bad url");
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("error getting products");
            System.out.println(e.getMessage());
        }
        // Item item = new Item("0111", "asdf", "$79.75");
        // System.out.println(item);
    }
}
