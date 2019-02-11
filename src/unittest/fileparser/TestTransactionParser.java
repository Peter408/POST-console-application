package unittest.fileparser;

import java.util.ArrayList;
import java.util.HashSet;

import fileparser.*;
import items.*;
import store.*;
import transaction.*;
import user.*;

public class TestTransactionParser {
    private static final String PRODUCT_TEST_FILE_PATH = "./src/unittest/fileparser/productTest.txt";
    private static final String TRANSACTION_TEST_FILE_PATH = "./src/unittest/fileparser/transactionTest.txt";
    private static Customer customer1 = new Customer("QWE");
    private static Customer customer2 = new Customer("QWE2");
    private static final Transaction TEST_CASE_OUTPUT1 = new Transaction(new Customer("QWE"), "CASH", 9.99, "NaN");
    private static final Transaction TEST_CASE_OUTPUT2 = new Transaction(new Customer("QWE2"), "CREDIT", 1519.00, "12345");
    private static final String TEST_CASE_FAIL_LOG = "transaction parsing failed";
    private static final String SUCCESS_LOG = "Success";
    private static final String FAIL_LOG = "Fail";
    public static void main( String[] args ) {
        try {
            ProductParser testProductParser = new ProductParser(PRODUCT_TEST_FILE_PATH);
            Store store = new Store("testCase");
            HashSet<Item> items = testProductParser.extractProducts();
            for (Item item : items) {
                store.addToInventory(item);
                store.addToCatalog(item);
            }
            TransactionParser testTransactionParser = new TransactionParser(TRANSACTION_TEST_FILE_PATH, store);
            ArrayList<Transaction> transactions = testTransactionParser.extractTransactions();
            if (!transactions.contains(TEST_CASE_OUTPUT1)) {
                System.out.println(transactions.get(0));
                System.out.println(TEST_CASE_OUTPUT1);
                System.out.println("a");
                throw new Exception(TEST_CASE_FAIL_LOG);
            }
            if (!transactions.contains(TEST_CASE_OUTPUT2)) {
                throw new Exception(TEST_CASE_FAIL_LOG);
            }
            System.out.println(SUCCESS_LOG);
        } catch( Exception exception) {
            exception.printStackTrace();
            System.out.println(FAIL_LOG);
        }
    }
}