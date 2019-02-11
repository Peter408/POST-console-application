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
  protected final String NAME = "~~~~ McBurgerTown Point of Sale Terminal ~~~~";
  protected final String STATUS = "\nSTATUS\nStore: ";
  protected final String INVALIDINPUT = "Input not recognized, valid input is a single digit number from 1 - 4";

  protected enum Page {
    MAIN, OPERATIONS, SHOPPING, CHECKOUT;
  }

  /*
    Runtime State
  */
  protected Page page = Page.MAIN;
  private Inputs currentInput;
  private Inputs[] inputs = {new MainMenu(this), new Operations(this), new Shopping(this), new Checkout(this)};
  private Scanner in;
  private POST post;
  private TransactionParser transactionParser;
  private ProductParser productParser;
  protected String storeState;
  protected String dbLocation;

  public Driver() {
    this.in = new Scanner(System.in);
    this.post = new POST();
    this.storeState = "CLOSED";
    this.dbLocation = "";
  }

  public void start(String[] args) {
    String path = getDatabasePath(args);
    initDatabase(path);
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

  private void initDatabase(String path) {
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
      setInput(page);
      this.currentInput.printPrompt();
      int input = in.nextInt();
      this.currentInput.run(input);
    }
  }

  private void setInput(Page page) {
    switch (page) {
      case MAIN:
        this.currentInput = this.inputs[0];
        break;
      case OPERATIONS:
        this.currentInput = this.inputs[1];
        break;
      case SHOPPING:
        this.currentInput = this.inputs[2];
        break;
      case CHECKOUT:
        this.currentInput = this.inputs[3];
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
