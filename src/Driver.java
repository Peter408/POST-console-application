
import java.util.Scanner;
import java.util.HashSet;
import java.util.Iterator;
import java.io.FileNotFoundException;

import post.POST;
import fileparser.TransactionParser;
import fileparser.ProductParser;
import items.Item;
import transaction.Transaction;

public class Driver {
  private final String NAME = "~~~~ McBurgerTown Point of Sale Terminal ~~~~";
  private final String DESC = "\nMain menu\nEnter a number to continue...";
  private final String MENU = "1: open store\n2: close store\n3: auto test\n4: log out";
  private final String STATUS = "\nSTATUS\nStore: ";
  private final String INVALIDINPUT = "Input not recognized, valid input is a single digit number from 1 - 4";

  private Scanner in = new Scanner(System.in);
  private POST post = new POST();
  private TransactionParser transactionParser;
  private ProductParser productParser;
  private String storeState = "CLOSED";
  private String dbLocation = "";

  public void start(String[] args) {
    String path = getDatabasePath(args);
    initDataBase(path);
    runMainMenu();
  }

  /*
  databasePath is relative to this folder, /src
  default path/ no path provided -> database is in /src
  */
  private String getDatabasePath(String[] args) {
    if (args.length > 0) {
      return args[0];
    } else {
      return "";
    }
  }

  private void initDataBase(String path) {
    initTransactionParser(path);
    initProductParser(path);
    setDbLocation(path);
  }

  private void initTransactionParser(String path) {
    try {
      this.transactionParser = new TransactionParser(path + "transactions.txt", post.getStore());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      System.exit(-1);
    }
  }

  private void initProductParser(String path) {
    try {
      this.productParser = new ProductParser(path + "products.txt");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      System.exit(-1);
    }
  }

  private void setDbLocation(String path) {
    this.dbLocation = "Database:\n    " + path + "transactions.txt\n    " + path + "products.txt";
  }

  private void runMainMenu() {
    System.out.println(NAME);
    while (true) {
      printPrompt();
      int choice = in.nextInt();
      runChoice(choice);
    }
  }

  private void printPrompt() {
    System.out.println(DESC);
    System.out.println(STATUS + storeState + "\n" + dbLocation + "\n");
    System.out.println(MENU);
  }

  private void runChoice(int choice) {
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

  private void openStore() {
    System.out.println("Opening Store...");
    this.storeState = "OPEN";
    this.post.openStore();
  }

  private void closeStore() {
    System.out.println("Closing Store...");
    this.storeState = "CLOSED";
    this.post.closeStore();
  }

  private void runTest() {
    System.out.println("Running tests...");
    HashSet<Item> items = productParser.extractProducts();

    for (Item item: items) {
      this.post.addItemToInventory(item);
      this.post.addItemToCatalog(item);
    }

    HashSet<Transaction> transactions = transactionParser.extractTransactions();
  }

  private void exit() {
    System.out.println("Logging off...");
    this.in.close();
    // TODO "Log off"
    System.exit(0);
  }
}
