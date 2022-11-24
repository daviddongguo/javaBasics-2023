package dong.java.lessons;

import dong.java.utilities.ConsoleOutput;
import dong.java.utilities.ConsoleInput;

public class Day01 {

  private Day01() {
  }

  public static void printFullName() {

    ConsoleOutput.println("please enter your lastName: ");
    String lastName = ConsoleInput.readLine();
    ConsoleOutput.println("please enter your firstName: ");
    String firstName = ConsoleInput.readLine();

    ConsoleOutput.println("Your name is : " + firstName + " " + lastName);
  }
}


