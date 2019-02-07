import FileParser.*;



public class TestInit {
    private static final String SUCCESS_LOG = "Success";
    private static final String FAIL_LOG = "Fail";
    public static void main( String[] args ) {
        try {
            new ProductParser();
            new TransactionParser();
            System.out.println(SUCCESS_LOG);
        } catch( Exception exception) {
            System.out.println(exception);
            System.out.println(FAIL_LOG);
        }
    }
}