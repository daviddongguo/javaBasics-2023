package com.dongguo.lesson;

import com.dongguo.utility.ConsoleInput;
import com.dongguo.utility.ConsoleOutput;

public class Lesson01HelloWorld {

  public static void printFullName() {

    ConsoleOutput.println("please enter your lastName: ");
    String lastName = ConsoleInput.readLine();
    ConsoleOutput.println("please enter your firstName: ");
    String firstName = ConsoleInput.readLine();

    ConsoleOutput.println("Your name is : " + firstName + " " + lastName);
  }

  private Lesson01HelloWorld() {}

}

