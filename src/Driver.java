
import java.util.Scanner;
import post.POST;

public class Driver {
  private static final String NAME = "~~~~ McBurgerTown Point of Sale Terminal ~~~~";
  private static final String DESC = "\nMain menu\nEnter a number to continue...";
  private static final String MENU = "1: open store\n2: close store\n3: auto test\n4: log out";

  private static String status = "\nSTATUS\nStore: CLOSED\n";

  public static void main(String[] args) {
    POST post = new POST();

    // specify database location


    // open, close, test
    System.out.println(NAME);

    while (true) {
      System.out.println(DESC);
      System.out.println(status);
      System.out.println(MENU);

      Scanner in = new Scanner(System.in);
      int choice = in.nextInt();

      switch(choice) {
        case 1:
          System.out.println("Opening Store...");
          status = "\nSTATUS\nStore: OPEN\n";
          post.openStore();
          break;
        case 2:
          System.out.println("Closing Store...");
          status = "\nSTATUS\nStore: CLOSED\n";
          post.closeStore();
          break;
        case 3:
          System.out.println("Parsing transactions.txt...");
          // do that
          break;
        case 4:
          System.out.println("Logging off...");
          // TODO "Log off"
          System.exit(0);
          break;
        default:
          System.out.println("Input not recognized, valid input is a single digit number from 1 - 4");
          break;
      }
    }
  }
}
