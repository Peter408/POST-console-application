package network;

import java.net.MalformedURLException;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import item.CartItem;
import item.Item;
import network.adapters.*;
import transaction.*;

public class Api {
    private Gson gson;
    private HashMap<String, URL> paths;

    public Api(String uri) throws MalformedURLException {
        URL base = new URL(uri);
        this.paths = new HashMap<>();
        registerPath(base, "products");
        registerPath(base, "payments");
        registerPath(base, "sales");
        this.gson = new GsonBuilder()
                .registerTypeAdapter(Item.class, new ItemAdapter())
                .registerTypeAdapter(CartItem.class, new CartItemAdapter())
                .registerTypeAdapter(Payment.class, new PaymentAdapter())
                .registerTypeAdapter(Transaction.class, new TransactionAdapter())
                .create();
    }

    private void registerPath(URL base, String key) throws MalformedURLException {
        this.paths.put(key, appendURL(base, "/" + key));
    }

    private URL appendURL(URL url, String extension) throws MalformedURLException {
        return new URL(url.toExternalForm() + extension);
    }

    public List<Item> getProducts() throws IOException {
        Get getHandler = new Get(this.paths.get("products"));
        Response res = getHandler.execute();
        TypeToken typeToken = new TypeToken<List<Item>>() {
        };
        return gson.fromJson(res.getBody(), typeToken.getType());
    }

    public int putTransaction(Transaction transaction) throws IOException {
        Put putHandler = new Put(this.paths.get("sales"));
        String body = this.gson.toJson(transaction);
        System.out.println(body);
        Response res = putHandler.execute(body);
        int id = this.gson.fromJson(res.getBody(), Id.class).id;
        return id;
    }

    private class Id {
        int id;
    }

    public boolean putPaymentType(Payment payment) throws IOException {
        if (payment.getPaymentType().equals("CASH")) return true;
        String body = this.gson.toJson(payment);
        try {
            URL url = appendURL(this.paths.get("payments"), "/" + payment.getPaymentType().toLowerCase());
            System.out.println(url);
            Post postHandler = new Post(url);
            Response res = postHandler.execute(body);
            System.out.println(res.getStatusCode() + res.getBody());
            switch (res.getStatusCode()) {
            case 202:
                return true;
            case 406:
                return false;
            case 400:
                throw new IOException("Malformed request: " + res.getBody());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
