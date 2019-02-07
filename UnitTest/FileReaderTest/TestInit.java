import FileReader.*;



public class TestInit {
    private static final String SUCCESS_LOG = "Success";
    private static final String FAIL_LOG = "Fail";
    public static void main( String[] args ) {
        try {
            ProductReader productReader = new ProductReader();
            TransactionReader transactionReader = new TransactionReader();
            System.out.println(SUCCESS_LOG);
        } catch( Exception exception) {
            System.out.println(exception);
            System.out.println(FAIL_LOG);
        }
    }
}