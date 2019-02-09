package unittest.fileparser;

import fileparser.*;

public class TestInit {
    private static final String PRODUCT_TEST_FILE_PATH = "./src/UnitTest/FileParserTest/productTest.txt";
    private static final String TRANSACTION_TEST_FILE_PATH = "./src/UnitTest/FileParserTest/transactionTest.txt";
    private static final String SUCCESS_LOG = "Success";
    private static final String FAIL_LOG = "Fail";
    public static void main( String[] args ) {
        try {
            new ProductParser(PRODUCT_TEST_FILE_PATH);
            new TransactionParser(TRANSACTION_TEST_FILE_PATH);
            System.out.println(SUCCESS_LOG);
        } catch( Exception exception) {
            System.out.println(exception);
            System.out.println(FAIL_LOG);
        }
    }
}