package driver;

abstract public class Inputs {
  protected static Driver driver;
  protected String description;
  protected String choices;

  abstract protected void run(int inputs);

  protected void printPrompt() {
    System.out.println(this.driver.NAME);
    System.out.println(this.description);
    System.out.println(this.driver.STATUS + this.driver.storeState + "\n" + this.driver.dbLocation + "\n");
    System.out.println(this.choices);
  }
}