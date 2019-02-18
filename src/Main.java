import gui.ShopFrame;
import gui.productsearch.AddItemFrame;
import item.Item;
import post.POST;

public class Main {
    public static void main(String[] args) {
        POST post = new POST("https://post-server.herokuapp.com");
        new ShopFrame(post);
        new AddItemFrame(post.getCatalog());
    }
}
