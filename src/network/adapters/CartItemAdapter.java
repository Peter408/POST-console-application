package network.adapters;

import com.google.gson.*;
import item.CartItem;
import item.Item;

import java.lang.reflect.Type;

public class CartItemAdapter implements JsonSerializer<CartItem>, JsonDeserializer<CartItem> {
    @Override
    public JsonElement serialize(CartItem cartItem, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject obj = new JsonObject();
        obj.add("upc", new JsonPrimitive(cartItem.getId()));
        obj.add("quantity", new JsonPrimitive(cartItem.getQuantity()));
        obj.add("price", new JsonPrimitive(cartItem.getUnitPrice()));
        return obj;
    }

    @Override
    public CartItem deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject obj = jsonElement.getAsJsonObject();
        String id = obj.get("upc").getAsString();
        double unitPrice = obj.get("price").getAsDouble();
        int quantity = obj.get("quantity").getAsInt();
        Item item = new Item(id, null, unitPrice);
        return new CartItem(item, quantity);
    }
}
