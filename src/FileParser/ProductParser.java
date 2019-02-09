package FileParser;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ProductParser extends FileParser {
    // private Object catalog;

    public ProductParser(String fileName) throws FileNotFoundException {
        super(fileName);
    }

    public Object extractProducts() {
        // catalog = new Object();
        String testCase = "";
        Object item;
        while(null != (item = parseProduct())) {
            // catalog.addItem(item);
            testCase = testCase.concat((String)item);
        }
        // return catalog;
        return testCase;
    }

    private Object parseProduct() {
        try {
            String id = parseUPC();
            String name = parseDescription();
            Double price = parsePrice();
            // return new Item(id, name, price);
            return String.format("id: %s\nname: %s\nprice: %.2f\n\n", id, name, price);
        } catch(Exception exception) {
            return null;
        } 
    }

    private String parseUPC() throws IOException {
        return parseSegment(4);
    }

    private String parseDescription() throws IOException {
        parseSegment(5);
        return parseSegment(20).trim();
    }

    private Double parsePrice() throws IOException {
        parseSegment(5);
        return Double.parseDouble(parseLine());
    }

}