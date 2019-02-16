package item;

import com.google.gson.Gson;

class GSONTest {
    public static void main(String[] args) {
        Gson gson = new Gson();
        Item item2 = gson.fromJson("{name: \"idk\", price: \"$10.00\", id: \"0002\"}", Item.class);
        System.out.println(item2);
    }
}