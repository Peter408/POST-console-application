import gui.ShopFrame;
import gui.productsearch.AddItemFrame;
import item.Item;
import post.POST;

public class Main {
    public static void main(String[] args) {
        POST post = new POST();
        Item apple = new Item("0001", "Apples", 5.00);
        Item orange = new Item("0002", "Oranges", 3.00);
        post.addItemToInventory(apple);
        post.addItemToInventory(orange);
        post.addItemToCatalog(apple);
        post.addItemToCatalog(orange);
        
        new ShopFrame( post );
        new AddItemFrame(post.getCatalog());
    }
}
