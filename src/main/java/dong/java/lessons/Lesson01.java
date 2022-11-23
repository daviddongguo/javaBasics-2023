package dong.java.lessons;

import dong.java.utilities.ConsoleOutput;
import dong.java.utilities.StandardInput;

public class Lesson01 {

  private Lesson01() {
  }

  public static void printFullName() {

    ConsoleOutput.println("please enter your lastName: ");
    String lastName = StandardInput.readLine();
    ConsoleOutput.println("please enter your firstName: ");
    String firstName = StandardInput.readLine();

    ConsoleOutput.println("Your name is : " + firstName + " " + lastName);
  }
}


