package driver;

public class Operations extends Inputs {

  protected Operations(Driver driver) {
    this.driver = driver;
    this.description = "\nPOST Operations\nEnter a number to continue...";
    this.choices = "1: add inventory\n2: remove inventory\n3: delete inventory\n4: add to catalog\n5: remove from catalog\n6: new transaction\n7: back to main menu";
  }

  protected void run(int input) {
    switch(input) {
      case 1:
        addToInventory();
        break;
      case 2:
        removeFromInventory();
        break;
      case 3:
        deleteFromInventory();
        break;
      case 4:
        addToCatalog();
        break;
      case 5:
        removeFromCatalog();
        break;
      case 6:
        newTransaction();
        break;
      case 7:
        this.driver.page = Driver.Page.MAIN;
        break;
      default:
        System.out.println(this.driver.INVALIDINPUT);
        break;
    }
  }

  private void addToInventory() {
    System.out.println("ADD");
  }

  private void removeFromInventory() {
    System.out.println("REMOVE");
  }

  private void deleteFromInventory() {
    System.out.println("DELETE");
  }

  private void addToCatalog() {
    System.out.println("ADDCATALOG");
  }

  private void removeFromCatalog() {
    System.out.println("REMOVECATALOG");
  }

  private void newTransaction() {
    System.out.println("NEW");
  }
}
