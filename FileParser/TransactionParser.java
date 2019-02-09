import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;

public class TransactionParser extends FileParser {
    // Object store

    public TransactionParser(String fileName) throws FileNotFoundException {
        super(fileName);
    }

    public HashSet<Object> extractTransactions() {
        return parseTransactions();
    }

    private HashSet<Object> parseTransactions() {
        HashSet<Object> transactions = new HashSet<>();
        Object transaction;
        while(null != (transaction = parseTransaction())) {
            transactions.add(transaction);
        }
        return transactions;
    }

    private Object parseTransaction() {
        try {
            String name = parseName();
            HashSet<Object> cartItems = parseItems();
            Object paymentInformation = parsePaymentInformation(getTotal(cartItems));
            // Object transaction = new Object(name, cartItems, paymentInformation);
            return String.format("%s\nitems:\n%s\n%s", name, cartItems, paymentInformation);
        } catch(Exception exception) {
            return null;
        }
    }

    private String parseName() throws IOException {
        return parseLine().trim();
    }

    private HashSet<Object> parseItems() throws IOException {
        HashSet<Object> cartItems = new HashSet<>();
        String firstCharacter;
        String UPC;
        String quantitySegment;
        Integer quantity;
        while (!"<".equals(firstCharacter = parseSegment(1))) {
            UPC = firstCharacter.concat(parseSegment(3));
            quantitySegment = parseLine();       
            if (5 < quantitySegment.length()) {
                quantity = Integer.valueOf(quantitySegment.substring(5));
            } else {
                quantity = 1;
            }
            // cartItems.add(new CartItem(UPC, quantity));
            cartItems.add(String.format("id:%s qty:%d", UPC, quantity));
        }
        return cartItems;
    }

    private Double getTotal(HashSet<Object> cartItems) {
        // todo filter through cartItems and return sum
        return 0.00;
    }

    private Object parsePaymentInformation(Double total) throws IOException {
        String paymentInfo = parseLine();
        Boolean cash = -1 != paymentInfo.indexOf('$');
        Boolean approved = '<' != parseLine().charAt(0);
        if (!approved) {
            nextLine();
        }
        String creditCardNumber = "NaN";
        if ( !cash ) {
            creditCardNumber = paymentInfo.substring(7, 12);
        }
        Double payment;
        if ( cash ) {
            payment = Double.valueOf(paymentInfo.substring(paymentInfo.indexOf('$') + 1, paymentInfo.length() - 1));
        } else {
            payment = total;
        }
        Double change = payment - total;
        // Object payment = new Object(cash, approved, creditCardNumber, total, payment, change);
        return String.format("payment info:\ncash: %b\napproved:%b\ncreditCardNumber:%s\ntotal:%.2f\npayment:%.2f\nchange:%.2f", cash, approved, creditCardNumber, total, payment, change);
    }

}