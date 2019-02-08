import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;
import java.util.Vector;

public class TransactionParser extends FileParser {
    // Object store

    public TransactionParser(String fileName) throws FileNotFoundException {
        super(fileName);
    }

    public Object extractTransactions() {
        return "";
    }

    private Object parseTransactions() {
        return "";
    }

    private Object parseTransaction() throws IOException {
        String name = parseName();
        Set<Object> cartItems = parseItems();
        Object paymentInformation = parsePaymentInformation();
        return "";
    }

    private String parseName() {
        return parseLine().trim();
    }

    private Set<Object> parseItems() throws IOException {
        Set<Object> cartItems = new Set<>();
        char firstCharacter;
        String UPC;
        Integer qantity;
        while ('<' != (firstCharacter = parseSegment(1))) {
            UPC = String.valueOf(firstCharacter) + parseSegment(3);
            parseSegment(5);
            quantity = Integer.getInteger(parseLine());
            // cartItems.add(new CartItem(UPC, quantity));
            cartItems.add(String.format("id:%s qty:%d", UPC, quantity));
        }
        return cartItems;
    }

    private Object parsePaymentInformation() {
        String paymentInfo = parseLine();
        bool cash = paymentInfo.indexOf('$');
        bool approved = '<' != parseLine().charAt(0);
        // int creditCardNumber = paymentInfo.substring(7, 12);
        // todo: I think payment needs to be splitted into cad payment and cash/check payment

    }

}