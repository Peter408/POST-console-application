

public class Driver {
  public static void main(String[] args) {
    Item itemOne, itemTwo, itemThree, itemFour;
    itemOne = new Item(0001, "Antiseptic", 3.00);
    itemTwo = new Item(0002, "Paper", 5.00);
    itemThree = new Item(0003, "Oranges", 2.00);
    itemFour = new Item(0004, "Trains", 4.99);

    Store myStore = new Store(McBurgerPlace);

    myStore.addToInventory(itemOne);
    myStore.addToInventory(itemTwo);
    
    myStore.addToCatalog(itemOne);
    myStore.addToCatalog(itemTwo);


  }
}
