
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashSet;
import java.io.FileNotFoundException;

import java.util.Random;

import post.POST;
import fileparser.TransactionParser;
import fileparser.ProductParser;
import item.Item;
import transaction.*;

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

  private static Random random = new Random();

  public void start(String[] args) {
    String path = getDatabasePath(args);
    initDataBase(path);
    runMainMenu();
  }

  /*
   * databasePath is relative to this folder, /src default path/ no path provided
   * -> database is in /src
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
    switch (choice) {
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

  private void exit() {
    System.out.println("Logging off...");
    this.in.close();
    // TODO "Log off"
    System.exit(0);
  }
}
