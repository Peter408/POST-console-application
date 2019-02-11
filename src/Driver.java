
import user.*;
import items.*;
import transaction.*;

public class Driver {
    public static void main(String[] args) {
        System.out.println("hello world");

        Customer customer1 = new Customer("james");
        Customer customer2 = new Customer("amanda");
        Customer customer3 = new Customer("alvin");

        Item item1 = new Item("123", "box", 20.13);
        Item item2 = new Item("234", "gym", 11.10);
        Item item3 = new Item("454", "toy", 10.11);
        Item item4 = new Item("111", "iPad", 199.99);


        customer1.addToCart(item1, 3);
        customer1.addToCart(item2, 1);
        customer1.addToCart(item3, 2);
        customer1.removeFromCart(item1, 2);
        customer1.removeFromCart(item2, 1);

        customer2.addToCart(item1, 1);
        customer2.addToCart(item2, 1);
        customer2.addToCart(item3, 1);

        customer3.addToCart(item1, 2);
        customer3.addToCart(item2, 1);
        customer3.addToCart(item4, 2);
        customer3.removeFromCart(item3, 1);
        customer3.removeFromCart(item1, 5);

        System.out.println(customer1.toString());
        System.out.println("total: " + customer1.getTotal() + "\n");

        System.out.println(customer2.toString());
        System.out.println("total: " + customer2.getTotal() + "\n");

        System.out.println(customer3.toString());
        System.out.println("total: " + customer3.getTotal() + "\n");

        System.out.println("TRANSACTION:\n");

        Transaction transaction1 = new Transaction(customer1, "CASH", 200.50);
        Transaction transaction2 = new Transaction(customer2, "CREDIT", "12345");
        Transaction transaction3 = new Transaction(customer3, "CHECK", 50.50);

        System.out.println("change: " + transaction1.getChange());
        System.out.println(transaction1.getTimestamp());
        System.out.println(transaction1.toString() + "\n");

        System.out.println("change: " + transaction2.getChange());
        System.out.println(transaction2.getTimestamp());
        System.out.println(transaction2.toString() + "\n");

        System.out.println("change: " + transaction3.getChange());
        System.out.println(transaction3.getTimestamp());
        System.out.println(transaction3.toString() + "\n");

        System.out.println("INVOICE: \n");

        Invoice invoice1 = new Invoice(transaction1);
        System.out.println(invoice1.displayInvoice());
    }
}