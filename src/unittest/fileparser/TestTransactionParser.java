package unittest.fileparser;

import java.util.ArrayList;
import java.util.HashSet;

import fileparser.*;
import item.*;
import store.*;
import transaction.*;
import user.*;

public class TestTransactionParser {
    private static final String PRODUCT_TEST_FILE_PATH = "./src/unittest/fileparser/productTest.txt";
    private static final String TRANSACTION_TEST_FILE_PATH = "./src/unittest/fileparser/transactionTest.txt";
    private static final Customer CUSTOMER1 = new Customer("QWE");
    private static final Customer CUSTOMER2 = new Customer("QWE2");
    private static final Item ITEM1 = new Item("1111", "Item 1", 15.20);
    private static final Item ITEM2 = new Item("1112", "Item 2 jygkbytiby", 111.00);
    private static final Item ITEM3 = new Item("i8ny", "yw3b5w 463 46w 36pb4", 22.22);
    static {
        CUSTOMER1.addToCart(ITEM1, 5);
        CUSTOMER1.addToCart(ITEM2, 13);
        CUSTOMER1.addToCart(ITEM3, 1);
        CUSTOMER2.addToCart(ITEM1, 5);
        CUSTOMER2.addToCart(ITEM2, 13);
    };
    private static final Transaction TEST_CASE_OUTPUT1 = new Transaction(CUSTOMER1, "CASH", 9.99, "NaN");
    private static final Transaction TEST_CASE_OUTPUT2 = new Transaction(CUSTOMER2, "CREDIT", 1519.00, "12345");
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
            if (!transactions.get(0).toString().equals(TEST_CASE_OUTPUT1.toString())) {
                throw new Exception(TEST_CASE_FAIL_LOG);
            }
            if (!transactions.get(1).toString().equals(TEST_CASE_OUTPUT2.toString())) {
                throw new Exception(TEST_CASE_FAIL_LOG);
            }
            System.out.println(SUCCESS_LOG);
        } catch( Exception exception) {
            exception.printStackTrace();
            System.out.println(FAIL_LOG);
        }
    }
}