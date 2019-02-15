package item;

import com.google.gson.Gson;

class GSONTest {
    public static void main(String[] args) {
        Item item = new Item("0001", "JSON object", 0);
        Gson gson = new Gson();
        System.out.println(gson.toJson(item));
        Item item2 = gson.fromJson("{name: \"idk\", price: 10.00, id: \"0002\"}", Item.class);
        System.out.println(item2);
    }
}