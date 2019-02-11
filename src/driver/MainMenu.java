package driver;

public class MainMenu extends Inputs {

  public MainMenu(Driver driver) {
    this.driver = driver;
  }

  public void run(int input) {
    switch(input) {
      case 1:
        this.driver.openStore();
        break;
      case 2:
        this.driver.closeStore();
        break;
      case 3:
        this.driver.runTest();
        break;
      case 4:
        this.driver.screen(Driver.Page.OPERATIONS);
        break;
      case 5:
        this.driver.exit();
        break;
      default:
        System.out.println(this.driver.INVALIDINPUT);
        break;
    }
  }
}
