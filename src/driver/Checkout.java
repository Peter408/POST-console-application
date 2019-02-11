package driver;

public class Checkout extends Inputs {

  protected Checkout(Driver driver) {
    this.driver = driver;
  }

  protected void run(int input) {
    switch(input) {
      case 1:
        cash();
        break;
      case 2:
        credit();
        break;
      case 3:
        check();
        break;
      case 4:
        cancel();
        break;
      default:
        System.out.println(this.driver.INVALIDINPUT);
        break;
    }
  }

  private void cash() {

  }

  private void credit() {

  }

  private void check() {

  }

  private void cancel() {
    this.driver.page = Driver.Page.OPERATIONS;
  }
}
