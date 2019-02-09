import FileParser.*;

public class TestTransactionParser {
    private static final String TRANSACTION_TEST_FILE_PATH = "./UnitTest/FileParserTest/transactionTest.txt";
    private static final String TEST_CASE_OUTPUT = "todo";
    private static final String TEST_CASE_FAIL_LOG = "transaction parsing failed";
    private static final String SUCCESS_LOG = "Success";
    private static final String FAIL_LOG = "Fail";
    public static void main( String[] args ) {
        try {
            TransactionParser testTransactionParser = new TransactionParser(TRANSACTION_TEST_FILE_PATH);
            System.out.println(testTransactionParser.extractTransactions());
            System.out.println(SUCCESS_LOG);
        } catch(Exception exception) {
            System.out.println(FAIL_LOG);
        }
    }
}