package user;

public class Manager extends User {
    Store store;

    public Manager(string name) {
        super(name);
    }

    boolean openStore(string storeName) {
        try {
            this.store = new Store(storeName);
        } catch(Exception exception) {
            return false;
        }
        return true;
    }

    boolean closeStore() {
        if( this.store.setOpen(false) ) {
            return true;
        }
        return false;
    }
}