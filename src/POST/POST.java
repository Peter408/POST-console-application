package post;

// import User;

/*
  Represents Point of Sale Terminal
 */

abstract public class POST {

  public final int postid;
  public User user;
  private int choice;
  private String menu;

  private static int counter = 0;

  public POST() {
    this.postid = counter++;
    this.user = new User();
  }

  public POST(User user) {
    this.postid = counter++;
    this.user = user;
  }

  public void printMenu() {
    System.out.println(this.menu);
  }

  public static void getUserChoice() {

  }

  public static void executeUserChoice() {

  }

  public static void checkout() {

  }
}
