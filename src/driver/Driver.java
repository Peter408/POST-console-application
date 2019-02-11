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
  private final String INVALIDINPUT = "Input not recognized, valid input is a single digit number from 1 - 4";

  private enum Page {
    MAIN, OPERATIONS;
  }

  private enum Operation {
    AddToInventory, RemoveFromInventory, DeleteFromInventory, AddToCatalog, RemoveFromCatalog, NewTransaction;
  }

  private Page page = Page.MAIN;
  private Scanner in = new Scanner(System.in);
  private POST post = new POST();
  private TransactionParser transactionParser;
  private ProductParser productParser;
  private String storeState = "CLOSED";
  private String dbLocation = "";

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

  private void screen(Page page) {
    this.page = page;
    while (this.page == page) {
      printPrompt();
      int input = in.nextInt();
      execute(input);
    }
  }

  private void screen(Operation operation) {
    // TODO route operations
  }

  private void execute(int input) {
    switch (this.page) {
      case MAIN:
        mainRouter(input);
        break;
      case OPERATIONS:
        operationsRouter(input);
        break;
    }
  }

  private void mainRouter(int input) {
    switch(input) {
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
        screen(Page.OPERATIONS);
        break;
      case 5:
        exit();
        break;
      default:
        System.out.println(INVALIDINPUT);
        break;
    }
  }

  private void operationsRouter(int input) {
    switch(input) {
      case 1:
        screen(Operation.AddToInventory);
        break;
      case 2:
        screen(Operation.RemoveFromInventory);
        break;
      case 3:
        screen(Operation.DeleteFromInventory);
        break;
      case 4:
        screen(Operation.AddToCatalog);
      case 5:
        screen(Operation.RemoveFromCatalog);
        break;
      case 6:
        screen(Operation.NewTransaction);
        break;
      case 7:
        this.page = Page.MAIN;
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
    HashSet<Transaction> transactions = transactionParser.extractTransactions();
    // TODO export invoices
  }

  private void exit() {
    System.out.println("Logging off...");
    this.in.close();
    // BEYOND SCOPE OF APPLICATION "Log off"
    System.exit(0);
  }
}
