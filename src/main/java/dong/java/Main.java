package dong.java;


public class Main {


  public static void main(String[] args) {
    StandardOutputStream.println("hello world");


//    printFullName();

  }

  private static void printFullName() {
    StandardOutputStream.println("please enter your lastName: ");
    String lastName = StandardInput.readLine();
    StandardOutputStream.println("please enter your firstName: ");
    String firstName = StandardInput.readLine();

    StandardOutputStream.println("Your name is : " + firstName + " " + lastName);
  }
}
