
import java.util.Scanner;

public class Driver {
  private static final String NAME = "~~~~ McBurgerTown Point of Sale Terminal ~~~~";
  private static final String DESC = "\nMain menu\nEnter a number to continue...";
  private static final String MENU = "1: open store\n2: close store\n3: auto test\n4: Log out";

  private POST post = new POST();



  public static void main(String[] args) {
    // specify database location


    // open, close, test
    System.out.println(NAME);

    while (true) {
      System.out.println(DESC);
      System.out.println(MENU);

      Scanner in = new Scanner(System.in);
      int choice = in.nextInt();

      switch(choice) {
        case 1:
          System.out.println("1");
          break;
        case 2:
          System.out.println("2");
          break;
        case 3:
          System.out.println("3");
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
