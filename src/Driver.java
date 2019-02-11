
import java.util.Scanner;
import java.io.FileNotFoundException;

import post.POST;
import fileparser.TransactionParser;
import fileparser.ProductParser;

public class Driver {
  private static final String NAME = "~~~~ McBurgerTown Point of Sale Terminal ~~~~";
  private static final String DESC = "\nMain menu\nEnter a number to continue...";
  private static final String MENU = "1: open store\n2: close store\n3: auto test\n4: log out";
  private static final String STATUS = "\nSTATUS\nStore: ";
  private static final String INVALIDINPUT = "Input not recognized, valid input is a single digit number from 1 - 4";

  private static POST post = new POST();
  private static TransactionParser tp;
  private static ProductParser pp;
  private static String storeState = "CLOSED";
  private static String dbLocation = "";

  public static void main(String[] args) {
    String path = getDatabasePath(args);
    initDataBase(path);
    runMainMenu();
  }

  /*
  databasePath is relative to this folder, /src
  default path/ no path provided -> database is in /src
  */
  private static String getDatabasePath(String[] args) {
    if (args.length > 0) {
      return args[0];
    } else {
      return "";
    }
  }

  private static void initDataBase(String path) {
    initTransactionParser(path);
    initProductParser(path);
    setDbLocation(path);
  }

  private static void initTransactionParser(String path) {
    try {
      tp = new TransactionParser(path + "transactions.txt");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      System.exit(-1);
    }
  }

  private static void initProductParser(String path) {
    try {
      pp = new ProductParser(path + "products.txt");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      System.exit(-1);
    }
  }

  private static void setDbLocation(String path) {
    dbLocation = "Database:\n    " + path + "transactions.txt\n    " + path + "products.txt";
  }

  private static void runMainMenu() {
    System.out.println(NAME);
    while (true) {
      printPrompt();
      int choice = getIntInput();
      runChoice(choice);
    }
  }

  private static int getIntInput() {
    Scanner in = new Scanner(System.in);
    return in.nextInt();
  }

  private static void printPrompt() {
    System.out.println(DESC);
    System.out.println(STATUS + storeState + "\n" + dbLocation + "\n");
    System.out.println(MENU);
  }

  private static void runChoice(int choice) {
    switch(choice) {
      case 1:
        openStore();
        break;
      case 2:
        closeStore();
        break;
      case 3:
        runTest();
        break;
      case 4:
        exit();
        break;
      default:
        System.out.println(INVALIDINPUT);
        break;
    }
  }

  private static void openStore() {
    System.out.println("Opening Store...");
    storeState = "OPEN";
    post.openStore();
  }

  private static void closeStore() {
    System.out.println("Closing Store...");
    storeState = "CLOSED";
    post.closeStore();
  }

  private static void runTest() {
    System.out.println("Parsing transactions.txt...");

  }

  private static void exit() {
    System.out.println("Logging off...");
    // TODO "Log off"
    System.exit(0);
  }
}
