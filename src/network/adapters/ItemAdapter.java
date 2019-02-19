package network.adapters;

import java.lang.reflect.Type;

import com.google.gson.*;
import item.Item;


public class ItemAdapter implements JsonSerializer<Item>, JsonDeserializer<Item> {
    @Override
    public JsonElement serialize(Item item, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject obj = new JsonObject();
        obj.add("upc", new JsonPrimitive(item.getId()));
        obj.add("description", new JsonPrimitive(item.getName()));
        obj.add("price", new JsonPrimitive(item.getPrice()));
        return obj;
    }

    @Override
    public Item deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject obj = jsonElement.getAsJsonObject();
        String name = obj.get("description").getAsString();
        String id = obj.get("upc").getAsString();
        String priceString = obj.get("price").getAsString();
        double price = Double.parseDouble(priceString.substring(1));
        return new Item(id, name, price);
    }
}

