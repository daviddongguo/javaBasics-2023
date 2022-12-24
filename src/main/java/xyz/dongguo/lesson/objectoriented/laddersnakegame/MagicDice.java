package xyz.dongguo.lesson.objectoriented.laddersnakegame;

import java.util.Scanner;

public class MagicDice implements IEarnable {

  Scanner scanner;

  public MagicDice(Scanner scanner) {
    this.scanner = scanner;
  }

  public int earnScore() {
    System.out.print("I'm Magic Dice, tell me which number do you love(1-6): ");
    return requestNumberBetween1And6();
  }

  private int requestNumberBetween1And6() {
    while (true) {
      int inputNumber = requestIntegerNumberInput();
      if (inputNumber >= 1 && inputNumber <= 6) {
        return inputNumber;
      }
      System.out.println("number must be between 1 and 6.");
    }
  }

  public int requestIntegerNumberInput() {
    while (true) {
      try {
        return Integer.parseInt(requestStringInput());
      } catch (NumberFormatException ex) {
        System.out.println("input must be an integer number.");
      }
    }
  }

  private String requestStringInput() {
    while (true) {
      String inputString = scanner.nextLine().trim();
      if (inputString.length() >= 1) {
        return inputString;
      }
      System.out.println("input can not be empty.");
    }
  }
}
