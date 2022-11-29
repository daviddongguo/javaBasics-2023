package xyz.dongguo.lesson;

import xyz.dongguo.utility.ConsoleOutput;

public class Day01 {

  private Day01() {
  }

  public static void printFullName() {

    String lastName = "WU";
    String firstName = "dongguo";

    ConsoleOutput.log("please enter your lastName: ");
    ConsoleOutput.log("please enter your firstName: ");

    ConsoleOutput.log(
        "Your name is : " + firstName + " " + lastName);
  }

  public static int operate(int x, int y) {
    return x + y;
  }
}


