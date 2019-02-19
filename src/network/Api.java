package network;

import java.net.MalformedURLException;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import item.CartItem;
import item.Item;
import transaction.Payment;
import transaction.Transaction;
import user.Customer;

public class Api {
    private Gson gson;
    private HashMap<String, URL> paths;

    public Api(String uri) throws MalformedURLException {
        URL base = new URL(uri);
        this.paths = new HashMap<>();
        putPath(base, "products");
        putPath(base, "payments");
        putPath(base, "transactions");
        this.gson = new Gson();
}

    public List<Item> getProducts() throws IOException {
        Get getHandler = new Get(this.paths.get("products"));
        List<ItemHelper> helpers =  getHelpers(getHandler.execute());
        return helpers.stream().map(ItemHelper::build).collect(Collectors.toList());
    }

    private List<ItemHelper> getHelpers(Response res) {
        String body = res.getBody();
        TypeToken<List<ItemHelper>> itemListType = new TypeToken<List<ItemHelper>>() {};
        Type type = itemListType.getType();
        return gson.fromJson(body, type);
    }

    public int putTransaction(Transaction transaction) throws IOException {
        String body = this.gson.toJson(new TransactionHelper(transaction));
        Put putHandler = new Put(this.paths.get("transactions"));
        Response res = putHandler.execute(body);
        return this.gson.fromJson(res.getBody(), Id.class).getId();
    }

    private class Id {
        int id;
        int getId() {
            return this.id;
        }
    }

    public void putPaymentType(Payment payment) throws IOException{
      String body = this.gson.toJson(new PaymentHelper(
        payment.getPaymentType(),
        payment.getPayment(),
        payment.getCardNumber()
      ));
      try {
        URL url = appendURL(this.paths.get("payments"), payment.getPaymentType());
        Put putHandler = new Put(url);
        Response res = putHandler.execute(body);
        System.out.println(res);
      } catch (MalformedURLException e) {
        e.printStackTrace();
      }
    }

    private void putPath(URL base, String key) throws MalformedURLException {
        this.paths.put(key, appendURL(base, "/" + key));
    }

    private URL appendURL(URL url, String extension) throws MalformedURLException {
      return new URL(url.toExternalForm() + extension);
    }

    private class ItemHelper {
        private String upc;
        private String description;
        private String price;

        ItemHelper(String upc, String description, String price) {
            this.upc = upc;
            this.description = description;
            this.price = price;
        }

        Item build() {
            return new Item(this.upc, this.description, Double.parseDouble(this.price.substring(1)));
        }
    }

    private class CartItemHelper {
        private int quantity;
        private String upc;
        private String price;

        CartItemHelper(CartItem item) {
            this.quantity = item.getQuantity();
            this.upc = item.getItem().getId();
            this.price = Double.toString(item.getItem().getPrice());
        }

        CartItem build() {
            Item item = new ItemHelper(this.upc, null, this.price).build();
            return new CartItem(item, quantity);
        }
    }

    private class PaymentHelper {
        private String type;
        private double amount;
        private String cardNumber;

        PaymentHelper(String type, double amount, String cardNumber) {
            this.type = type;
            this.amount = amount;
            this.cardNumber = cardNumber;
        }

        Payment build() {
            return new Payment(type, amount, cardNumber);
        }
    }

    private class TransactionHelper {
        private String customer;
        private String timeOfSale;
        private List<CartItemHelper> items;
        private double total;
        private PaymentHelper tendered;
        private double returned;

        public Transaction build() {
            Customer c = new Customer(customer);
            List<CartItem> cartItems = items.stream().map(CartItemHelper::build).collect(Collectors.toList());
            c.getCart().setCartItems(cartItems);
            Payment p = tendered.build();
            return new Transaction(c, p.getPaymentType(), p.getCardNumber());
        }

        TransactionHelper(Transaction t) {
            this.customer = t.getCustomer().getName();
            this.timeOfSale = t.getTimestamp();
            this.items = t.getCustomer().getCart().getPurchases().stream().map(CartItemHelper::new)
                    .collect(Collectors.toList());
            this.total = t.getTotal();
            this.tendered = new PaymentHelper(t.getPaymentType(), t.getPayment(), t.getCardNumber());
        }
    }
}
