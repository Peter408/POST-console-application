package post;

interface Strategy {
  static boolean checkout(User user) {
    // do transaction
    return false;
  }

  static boolean open();
  static boolean close();
}
