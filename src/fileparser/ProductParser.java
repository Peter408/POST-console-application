package fileparser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;

import item.Item;

public class ProductParser extends FileParser {
    public ProductParser(String fileName) throws FileNotFoundException {
        super(fileName);
    }

    public HashSet<Item> extractProducts() {
        HashSet<Item> products = new HashSet<>();
        Item item;
        while(null != (item = parseProduct())) {
            products.add(item);
        }
        return products;
    }

    private Item parseProduct() {
        try {
            String id = parseUPC();
            String name = parseDescription();
            Double price = parsePrice();
            return new Item(id, name, price);
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
        return Double.parseDouble(parseLine().trim());
    }
}