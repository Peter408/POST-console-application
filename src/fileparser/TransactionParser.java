package fileparser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;

import store.*;
import transaction.*;
import user.Customer;

public class TransactionParser extends FileParser {
    Store store;

    public TransactionParser(String fileName, Store storeParameter) throws FileNotFoundException {
        super(fileName);
        store = storeParameter;
    }

    public HashSet<Transaction> extractTransactions() {
        return parseTransactions();
    }

    private HashSet<Transaction> parseTransactions() {
        HashSet<Transaction> transactions = new HashSet<>();
        Transaction transaction;
        while(null != (transaction = parseTransaction())) {
            transactions.add(transaction);
        }
        return transactions;
    }

    private Transaction parseTransaction() {
        try {
            Customer customer = new Customer(parseName());
            parseItems(customer);
            String paymentType = parsePaymentType();
            switch(paymentType) {
                case "CASH":
                case "CHECK":
                    return new Transaction(customer, parsePaymentType(), parsePaymentSum(), "NaN");
                case "CREDIT":
                    return new Transaction(customer, parsePaymentType(), customer.getTotal(), parseCreditCardNumber());
                default:
                    return null;
            }
            
        } catch(Exception exception) {
            return null;
        }
    }

    private String parseName() throws IOException {
        return parseLine().trim();
    }

    private void parseItems(Customer customer) throws IOException {
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
            customer.addToCart(store.searchItem(UPC), quantity);
        }
    }

    private String parsePaymentType() throws IOException {
        switch (parseSegment(4)) {
            case "CASH":
                parseSegment(2);
                return "CASH";
            case "CHEC":
                parseSegment(3);
                return "CHECK";
            case "CRED":
                parseSegment(3);
                return "CREDIT";
            default:
                return "NaN";
        }
    }

    private double parsePaymentSum() throws IOException {
        String sum = parseLine();
        return Double.parseDouble(sum.substring(0, sum.length() - 1));
    }

    private String parseCreditCardNumber() throws IOException {
        String creditCardNumber = parseLine();
        return creditCardNumber.substring(0, creditCardNumber.length() - 1);
    }
}