package driver;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.io.FileNotFoundException;

import java.util.Random;

import post.POST;
import fileparser.TransactionParser;
import fileparser.ProductParser;
import item.Item;
import transaction.*;

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
   * Runtime State
   */
  protected Page page = Page.MAIN;
  private Inputs currentInput;
  private HashMap<Page, Inputs> inputs;
  private Scanner in;
  private POST post;
  private TransactionParser transactionParser;
  private ProductParser productParser;
  protected String storeState;
  protected String productsPath;
  protected String transactionsPath;
  protected String dbLocation;
  // only run tests and not menu
  private boolean onlyTest = false;

  public Driver() {
    this.in = new Scanner(System.in);
    this.post = new POST();
    this.storeState = "CLOSED";
    this.dbLocation = "";
    this.inputs = new HashMap<Page, Inputs>();
    this.inputs.put(Page.MAIN, new MainMenu(this));
    this.inputs.put(Page.OPERATIONS, new Operations(this));
    this.inputs.put(Page.SHOPPING, new Shopping(this));
    this.inputs.put(Page.CHECKOUT, new Checkout(this));
  }

  private static Random random = new Random();

  public void start(String[] args) {
    setDatabasePath(args);
    initDatabase(this.productsPath, this.transactionsPath);
    if (onlyTest) {
      this.runTest();
      System.exit(0);
    }
    screen(this.page);
  }

  /*
   * databasePath is relative to this folder, /src default path/ no path provided
   * -> database is in /src
   */

  private void setDatabasePath(String[] args) {
    if (args.length != 2 && args.length != 0) {
      System.err.println(
          "Invalid number of command line arguments: please enter the path to the list of products (1) and then the list of transactions (2).");
      System.exit(-1);
    }
    if (args.length == 2) {
      this.productsPath = args[0];
      this.transactionsPath = args[1];
      this.onlyTest = true;
    } else {
      this.productsPath = "db/products.txt";
      this.transactionsPath = "db/transactions.txt";
    }
  }

  private void initDatabase(String productsPath, String transactionsPath) {
    initTransactionParser(transactionsPath);
    initProductParser(productsPath);
    setDbLocation(productsPath, transactionsPath);
  }

  private void initTransactionParser(String path) {
    try {
      this.transactionParser = new TransactionParser(path, post.getStore());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      System.exit(-1);
    }
  }

  private void initProductParser(String path) {
    try {
      this.productParser = new ProductParser(path);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      System.exit(-1);
    }

    HashSet<Item> items = productParser.extractProducts();

    for (Item item : items) {
      this.post.addItemToInventory(item);
      this.post.addItemToCatalog(item);
    }
  }

  private void setDbLocation(String productsPath, String transactionsPath) {
    this.dbLocation = "Database:\n" + productsPath + "\n" + transactionsPath;
  }

  protected void screen(Page page) {
    this.page = page;
    while (this.page == page) {
      display(page);
      int input = in.nextInt();
      this.currentInput.run(input);
    }
  }

  private void display(Page page) {
    this.currentInput = this.inputs.get(page);
    this.currentInput.printPrompt();
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
    try {
      productParser.restart();
      transactionParser.restart();
      HashSet<Item> items = productParser.extractProducts();
      for (Item item : items) {
        this.post.addItemToInventory(item);
        this.post.addItemToCatalog(item);
      }
      ArrayList<Transaction> transactions = transactionParser.extractTransactions();
      for (Transaction transaction : transactions) {
        if (this.shouldReject(transaction)) {
          transaction.setApproved(false);
        }
        Invoice invoice = new Invoice(transaction);
        System.out.println(invoice.displayInvoice());
      }
    } catch (FileNotFoundException e) {
      System.out.println("Unable to find transactions file: " + e.getMessage());
    }

  }

  private boolean shouldReject(Transaction transaction) {
    double rand = Driver.random.nextDouble();
    return rand < 0.1;
  }

  protected void exit() {
    System.out.println("Logging off...");
    this.in.close();
    // BEYOND SCOPE OF APPLICATION "Log off"
    System.exit(0);
  }
}
