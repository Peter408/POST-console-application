package post;

// import User;

abstract public class POST {

  public final int postid;
  private static int idCount = 0;

  private enum Choice {
    CHECKOUT, OPEN, CLOSE;
  }

  public POST() {
    this.postid = idCount++;
  }

  public boolean action(User user, Choice choice) {
    Strategy strategy;
    if (isManager(user)) {
      strategy = new ManagerStrategy();
    } else {
      strategy = new CustomerStrategy();
    }

    switch (choice) {
      case Choice.CHECKOUT:
        return strategy.checkout(user);
        break;
      case Choice.OPEN:
        return strategy.open();
        break;
      case Choice.CLOSE:
        return strategy.close();
        break;
    }
  }

  private boolean isManager(User user) {
    return user instanceof Manager;
  }
}
