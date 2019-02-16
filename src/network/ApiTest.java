package network;

import java.io.IOException;
import java.net.MalformedURLException;
import item.Item;

class ApiTest {
    public static void main(String[] args) {
        try {
            Api api = new Api("http://localhost:3000");
            System.out.println(api.getProducts());
        } catch (MalformedURLException e) {
            System.out.println("bad url");
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("error getting products");
            System.out.println(e.getMessage());
        }
        Item item = new Item("0111", "asdf", "$79.75");
        System.out.println(item);
    }
}