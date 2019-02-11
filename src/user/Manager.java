package user;

import store.Store;

public class Manager extends User {
    private Store store;

    public Manager(String name) {
        super(name);
    }

    boolean openStore(String storeName) {
        try {
            this.store = new Store(storeName);
        } catch(Exception exception) {
            return false;
        }
        return true;
    }

    
    boolean closeStore() {
        if( this.store.close() ) {
            return true;
        }
        return false;
    }
}