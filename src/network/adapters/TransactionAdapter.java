package network.adapters;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import item.CartItem;
import transaction.Payment;
import transaction.Transaction;
import user.Customer;

import java.lang.reflect.Type;
import java.util.List;

public class TransactionAdapter implements JsonSerializer<Transaction>, JsonDeserializer<Transaction> {
    @Override
    public JsonElement serialize(Transaction transaction, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject obj = new JsonObject();
        obj.add("customer", new JsonPrimitive(transaction.getCustomer().getName()));
        obj.add("timeOfSale", new JsonPrimitive(transaction.getTimestamp()));
        TypeToken typeToken = new TypeToken<List<CartItem>>() {
        };
        JsonElement items = jsonSerializationContext.serialize(transaction.getCustomer().getCart().getPurchases(), typeToken.getType());
        obj.add("items", items);
        obj.add("total", new JsonPrimitive(transaction.getTotal()));
        JsonElement payment = jsonSerializationContext.serialize(transaction.getPayment(), Payment.class);
        obj.add("tendered", payment);
        obj.add("returned", new JsonPrimitive(transaction.getPayment().getChange()));
        return obj;
    }

    @Override
    public Transaction deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject obj = new JsonObject();
        String customerName = obj.get("customer").getAsString();
        String timeOfSale = obj.get("timeOfSale").getAsString();
        TypeToken typeToken = new TypeToken<List<CartItem>>() {
        };
        List<CartItem> items = jsonDeserializationContext.deserialize(obj.get("items"), typeToken.getType());
        double total = obj.get("total").getAsDouble();
        Payment payment = jsonDeserializationContext.deserialize(obj.get("tendered"), Payment.class);
        double returned = obj.get("returned").getAsDouble();
        Customer customer = new Customer(customerName);
        customer.getCart().setCartItems(items);
        return new Transaction(customer, payment);
    }
}
