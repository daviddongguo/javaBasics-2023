package xyz.dongguo.lesson;

import java.util.Random;
import xyz.dongguo.utility.ConsoleOutput;

public class Day03 {

  private static final Random random = new Random();

  private Day03() {
  }

  public static void printSubtractions() {
    int numberA = random.nextInt(99);
    int numberB = random.nextInt(99);
    while (numberA < numberB) {
      numberB = random.nextInt(99);
    }
    int result = calculate(numberA, numberB, Operator.MINUS);
    String problem = getProblem(numberA, numberB, Operator.MINUS);
    ConsoleOutput.log(String.format("%s is %s", problem, result));

  }


  private static String getProblem(int numberA, int numberB, Operator operator) {
    switch (operator) {
      case ADD:
        return String.format("%d + %d", numberA, numberB);
      case MINUS:
        return String.format("%d - %d", numberA, numberB);
      case MULTIPLY:
        return String.format("%d * %d", numberA, numberB);
      default:
        return String.format("%d / %d", numberA, numberB);
    }
  }

  private static int calculate(int numberA, int numberB, Operator operator) {
    switch (operator) {
      case ADD:
        return numberA + numberB;
      case MINUS:
        return numberA - numberB;
      case MULTIPLY:
        return numberA * numberB;
      default:
        return numberA / numberB;
    }
  }

  public static void printRandomHiFive() {
    int randomNumber = random.nextInt(99);
    String message = "";
    if (randomNumber % 5 == 0) {
      message = "HiFive";
    } else if (randomNumber % 2 == 0) {
      message = "HiEven";
    }

    ConsoleOutput.log(String.format("%d is %s", randomNumber, message));
  }

  public static int operteTwoNumbers() {

    int numberA = random.nextInt(10);
    int numberB = random.nextInt(10);
    int indexOfOperator = random.nextInt(3);
    Operator operator = getOperator(indexOfOperator);

    ConsoleOutput.log(getProblem(numberA, numberB, operator));
    return calculate(numberA, numberB, operator);
  }

  private static Operator getOperator(int indexOfOperator) {
    if (indexOfOperator == 0) {
      return Operator.ADD;
    }
    if (indexOfOperator == 1) {
      return Operator.MINUS;
    }
    if (indexOfOperator == 2) {
      return Operator.MULTIPLY;
    }
    return Operator.DIVIDE;
  }

  private static void printProblem(int numberA, int numberB, Operator operator) {
    switch (operator) {
      case ADD:
        ConsoleOutput.log(String.format("%d +  %d", numberA, numberB));
        return;
      case MINUS:
        ConsoleOutput.log(String.format("%d -  %d", numberA, numberB));
        return;
      case MULTIPLY:
        ConsoleOutput.log(String.format("%d *  %d", numberA, numberB));
        return;
      default:
        ConsoleOutput.log(String.format("%d /  %d", numberA, numberB));
    }
  }

  enum Operator {
    ADD,
    MINUS,
    MULTIPLY,
    DIVIDE,
  }
}
