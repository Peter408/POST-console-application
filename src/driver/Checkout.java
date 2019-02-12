package driver;

public class Checkout extends Inputs {

    protected Checkout(Driver driver) {
        this.driver = driver;
        this.description = "\nCheckout\nEnter a number to continue...";
        this.choices = "1: cash\n2: credit\n3: check\n4: back to transaction";
    }

    @Override
    protected void run(int input) {
        switch (input) {
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
        this.driver.page = Driver.Page.SHOPPING;
    }
}
