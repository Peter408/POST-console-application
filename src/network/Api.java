package network;

import java.net.MalformedURLException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import item.Item;

public class Api {
    private Gson gson;
    private static final String PRODUCTS_PATH = "/products";
    private static final String TRANSACTIONS_PATH = "/products";
    private URL baseUri;
    private URL productsUrl;

    public Api(String uri) throws MalformedURLException {
        this.baseUri = new URL(uri);
        this.productsUrl = new URL(this.baseUri.toExternalForm() + PRODUCTS_PATH);
        this.gson = new Gson();
    }

    class ItemHelper {
        private String upc;
        private String description;
        private String price;

        public ItemHelper(String upc, String description, String price) {
            this.upc = upc;
            this.description = description;
            this.price = price;
        }

        public Item makeItem() {
            return new Item(this.upc, this.description, Double.parseDouble(this.price.substring(1)));
        }

        @Override
        public String toString() {
            return String.format("upc: %-5s, description: %-20s, price: %-7s%n", this.upc, this.description,
                    this.price);
        }
    }

    public List<Item> getProducts() throws IOException {
        Get getHandler = new Get(this.productsUrl);
        Response res = getHandler.execute();
        String body = res.getBody();
        TypeToken<List<ItemHelper>> itemListType = new TypeToken<List<ItemHelper>>() {
        };
        Type type = itemListType.getType();
        List<ItemHelper> helpers = gson.fromJson(body, type);
        return helpers.stream().map(i -> i.makeItem()).collect(Collectors.toList());
    }
}