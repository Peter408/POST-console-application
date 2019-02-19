import gui.ShopFrame;
import post.POST;

public class Driver {
    public static void main(String[] args) {
        POST post = new POST("https://post-server.herokuapp.com");
        post.initializeProductList();
        new ShopFrame(post);
    }
}
