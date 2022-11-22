package dong.java;


public class Main {

  public static void main(String[] args) {
    // write your code here
    Logger.info("hello world");

    printFullName();

  }

  private static void printFullName() {
    Logger.info("please enter your lastName: ");
    String lastName = StandardInput.readLine();
    Logger.info("please enter your firstName: ");
    String firstName = StandardInput.readLine();

    Logger.info("Your name is : " + firstName + " " + lastName);
  }
}
