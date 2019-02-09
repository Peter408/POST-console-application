package UnitTest.FileParserTest;

import FileParser.*;

public class TestProductParser {
    private static final String PRODUCT_TEST_FILE_PATH = "./src/UnitTest/FileParserTest/productTest.txt";
    private static final String TEST_CASE_OUTPUT = 
        String.format("id: %s\nname: %s\nprice: %.2f\n\n", "1111", "Item 1", Double.parseDouble("15.20")) + 
        String.format("id: %s\nname: %s\nprice: %.2f\n\n", "1112", "Item 2 jygkbytiby", Double.parseDouble("111111111")) +
        String.format("id: %s\nname: %s\nprice: %.2f\n\n", "i8ny", "yw3b5w 463 46w 36pb4", Double.parseDouble("22.22"));
    private static final String TEST_CASE_FAIL_LOG = "product parsing failed";
    private static final String SUCCESS_LOG = "Success";
    private static final String FAIL_LOG = "Fail";
    public static void main( String[] args ) {
        try {
            ProductParser testProductParser = new ProductParser(PRODUCT_TEST_FILE_PATH);
            if (!TEST_CASE_OUTPUT.equals(testProductParser.extractProducts())) {
                throw new Exception(TEST_CASE_FAIL_LOG);
            }
            System.out.println(SUCCESS_LOG);
        } catch( Exception exception) {
            System.out.println(exception);
            System.out.println(FAIL_LOG);
        }
    }
}