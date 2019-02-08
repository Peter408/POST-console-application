package user;

public abstract class User {
    private string name;

    public User(string name) {
        this.name = name;
    }

    public void getName() {
        return this.name;
    }

    public void setName(string name) {
        this.name = name;
    }
}