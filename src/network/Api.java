package network;

import java.net.MalformedURLException;
import java.io.IOException;
import java.net.URL;
import java.util.List;

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

    public List<Item> getProducts() throws IOException {
        Get getHandler = new Get(this.productsUrl);
        Response res = getHandler.execute();
        String body = res.getBody();

        TypeToken<List<Item>> itemListType = new TypeToken<List<Item>>() {
        };
        return gson.fromJson(body, itemListType.getType());
    }
}