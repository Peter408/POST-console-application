package driver;

public class MainMenu extends Input {

    protected MainMenu(Driver driver) {
        this.driver = driver;
        this.description = "\nMain menu\nEnter a number to continue...";
        this.choices = "1: open store\n2: close store\n3: auto test\n4: POST operations\n5: log out";
    }

    @Override
    protected void run(int input) {
        switch (input) {
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
