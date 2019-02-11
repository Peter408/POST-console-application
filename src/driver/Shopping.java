package driver;

public class Shopping extends Inputs {

  protected Shopping(Driver driver) {
    this.driver = driver;
  }

  protected void run(int input) {
    switch(input) {
      case 1:
        getProducts();
        break;
      case 2:
        searchForProduct();
        break;
      case 3:
        addToCart();
        break;
      case 4:
        removeFromCart();
        break;
      case 5:
        checkout();
        break;
      case 6:
        cancel();
        break;
      default:
        System.out.println(this.driver.INVALIDINPUT);
        break;
    }
  }

  private void getProducts() {

  }

  private void searchForProduct() {

  }

  private void addToCart() {

  }

  private void removeFromCart() {

  }

  private void checkout() {

  }

  private void cancel() {
    this.driver.page = Driver.Page.OPERATIONS;
  }
}
