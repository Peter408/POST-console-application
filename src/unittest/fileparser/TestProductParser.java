package unittest.fileparser;

import java.util.HashSet;

import fileparser.*;
import items.Item;

public class TestProductParser {
    private static final String PRODUCT_TEST_FILE_PATH = "./src/unittest/fileparser/productTest.txt";
    private static final Item TEST_CASE_OUTPUT1 = new Item("1111", "Item 1", Double.parseDouble("15.20"));
    private static final Item TEST_CASE_OUTPUT2 = new Item("1112", "Item 2 jygkbytiby", Double.parseDouble("111111111"));
    private static final Item TEST_CASE_OUTPUT3 = new Item("i8ny", "yw3b5w 463 46w 36pb4", Double.parseDouble("22.22"));
    private static final String TEST_CASE_FAIL_LOG = "product parsing failed";
    private static final String SUCCESS_LOG = "Success";
    private static final String FAIL_LOG = "Fail";
    public static void main( String[] args ) {
        try {
            ProductParser testProductParser = new ProductParser(PRODUCT_TEST_FILE_PATH);
            HashSet<Item> products = testProductParser.extractProducts();
            if (!products.contains(TEST_CASE_OUTPUT1)) {
                throw new Exception(TEST_CASE_FAIL_LOG);
            }
            if (!products.contains(TEST_CASE_OUTPUT2)) {
                throw new Exception(TEST_CASE_FAIL_LOG);
            }
            if (!products.contains(TEST_CASE_OUTPUT3)) {
                throw new Exception(TEST_CASE_FAIL_LOG);
            }
            System.out.println(SUCCESS_LOG);
        } catch( Exception exception) {
            System.out.println(exception);
            System.out.println(FAIL_LOG);
        }
    }
}