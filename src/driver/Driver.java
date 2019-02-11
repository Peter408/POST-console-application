package driver;

import java.util.Scanner;
import java.util.HashSet;
import java.util.Iterator;
import java.io.FileNotFoundException;

import post.POST;
import fileparser.TransactionParser;
import fileparser.ProductParser;
import items.Item;
import transaction.Transaction;

/*
  Driver is the middleware between the POST and the UI
*/
public class Driver {
  private final String NAME = "~~~~ McBurgerTown Point of Sale Terminal ~~~~";
  private final String STATUS = "\nSTATUS\nStore: ";
  protected final String INVALIDINPUT = "Input not recognized, valid input is a single digit number from 1 - 4";

  protected enum Page {
    MAIN, OPERATIONS;
  }

  protected Page page = Page.MAIN;
  private Inputs inputs;
  private Scanner in;
  private POST post;
  private TransactionParser transactionParser;
  private ProductParser productParser;
  private String storeState;
  private String dbLocation;

  public Driver() {
    this.in = new Scanner(System.in);
    this.post = new POST();
    this.storeState = "CLOSED";
    this.dbLocation = "";
  }

  public void start(String[] args) {
    String path = getDatabasePath(args);
    initDataBase(path);
    screen(this.page);
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

    HashSet<Item> items = productParser.extractProducts();

    for (Item item: items) {
      this.post.addItemToInventory(item);
      this.post.addItemToCatalog(item);
    }
  }

  private void setDbLocation(String path) {
    this.dbLocation = "Database:\n    " + path + "transactions.txt\n    " + path + "products.txt";
  }

  protected void screen(Page page) {
    this.page = page;
    while (this.page == page) {
      printPrompt();
      int input = in.nextInt();
      execute(input);
    }
  }

  private void printPrompt() {
    System.out.println(this.NAME);
    String description;
    String choices;

    switch(this.page) {
      case MAIN:
        description = "\nMain menu\nEnter a number to continue...";
        choices = "1: open store\n2: close store\n3: auto test\n4: POST operations\n5: log out";
        promptOptions(description, choices);
        break;
      case OPERATIONS:
        description = "\nPOST Operations\nEnter a number to continue...";
        choices = "1: add inventory\n2: remove inventory\n3: delete inventory\n4: add to catalog\n5: remove from catalog\n6: new transaction\n7: back to main menu";
        promptOptions(description, choices);
        break;
    }
  }

  private void promptOptions(String description, String choices) {
    System.out.println(description);
    System.out.println(this.STATUS + this.storeState + "\n" + this.dbLocation + "\n");
    System.out.println(choices);
  }

  private void execute(int input) {
    switch (this.page) {
      case MAIN:
        this.inputs = new MainMenu(this);
        this.inputs.run(input);
        break;
      case OPERATIONS:
        this.inputs = new Operations(this);
        this.inputs.run(input);
        break;
    }
  }

  protected void openStore() {
    System.out.println("Opening Store...");
    this.storeState = "OPEN";
    this.post.openStore();
  }

  protected void closeStore() {
    System.out.println("Closing Store...");
    this.storeState = "CLOSED";
    this.post.closeStore();
  }

  protected void runTest() {
    System.out.println("Running tests...");
    HashSet<Transaction> transactions = transactionParser.extractTransactions();
    // TODO export invoices
  }

  protected void exit() {
    System.out.println("Logging off...");
    this.in.close();
    // BEYOND SCOPE OF APPLICATION "Log off"
    System.exit(0);
  }
}
