package user;

public class Manager extends User {
    Store store;

    public Manager(String name) {
        super(name);
    }

    // or some shit like this, don't have store
    boolean openStore(String storeName) {
        try {
            this.store = new Store(storeName);
        } catch(Exception exception) {
            return false;
        }
        return true;
    }

    // or some shit like, don't have store
    boolean closeStore() {
        if( this.store.setOpen(false) ) {
            return true;
        }
        return false;
    }
}