package xyz.dongguo.lesson;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Demo class
 *
 * @author Dongguo
 * @date 2022/12/02
 */
public class Day05Quiz {

  private final PrintStream out;
  private final Scanner scanner;
  private final ThreadLocalRandom random;

  public Day05Quiz(PrintStream out, InputStream in) {
    this.out = out;
    this.scanner = new Scanner(in);
    this.random = ThreadLocalRandom.current();
  }

  public Random getRandom() {
    return this.random;
  }

  public void printInput() {
    out.printf("%nPlease enter any string: ");
    out.println(scanner.nextLine());
  }

  public void calculateNumberUntilQuizzed() {
    int inputNumber = 0;
    int sum = 0;
    // 1st: False || True
    // 2st: True
    // end: False || False
    while (inputNumber != 0 || sum == 0) {
      System.out.print("Enter an integer number to calculate(0 for quit) :");
      inputNumber = scanner.nextInt();
      sum += inputNumber;
    }
    System.out.printf("The sum is %d%n", sum);
  }

  public void guessRandomNumber() {
    int rndNumber = random.nextInt(101);
    guessNumber(rndNumber, System.in, System.out);
  }

  public void guessNumber(int expectedNumber, InputStream in, PrintStream out) {
    out.printf("rand number is %d%n", expectedNumber);
    int inputNumber;
    out.print("Please enter a number between 0 and 100: ");
    Scanner keyboard = new Scanner(in);
    inputNumber = keyboard.nextInt();
    while (true) {
      if (expectedNumber == inputNumber) {
        out.printf("You get it: %d", expectedNumber);
        break;
      }
      if (expectedNumber < inputNumber) {
        out.println("too high");
      } else {
        out.println("too low");
      }
      out.print("Please enter a new  number: ");
      inputNumber = keyboard.nextInt();
    }
    keyboard.close();
  }

  public void subtractionQuiz() {
    final int numberOfQuestions = 1;
    int amountOfCorrect = 0;
    StringBuilder report = new StringBuilder();
    for (int i = 0; i < numberOfQuestions; i++) {
      int numberA = getRandomNumberBetween0And9(this.random);
      int numberB = getRandomNumberBetween0And9(this.random);
      if (numberA < numberB) {
        int tmp = numberA;
        numberA = numberB;
        numberB = tmp;
      }
      int subtractionOfTwoNumber = numberA - numberB;

      String questionString = String.format("%d)  %d - %d = ", i + 1, numberA, numberB);
      int userInput = MidEvaluation.requestIntegerNumberInput(questionString);

      // log user answer in report string
      if (subtractionOfTwoNumber == userInput) {
        amountOfCorrect++;
        report.append("& ").append(questionString).append(userInput)
           .append(String.format("\t\t\tY\t\t%d%n", amountOfCorrect));
      } else {
        report.append("& ").append(questionString).append(userInput).append("\t\t\tX\n");
      }
    }

    out.printf("You  have %d correct answers.%n", amountOfCorrect);
    out.println(report);
  }

  public int getRandomNumberBetween0And9(Random random) {
    return getRandomNumber(random, 0, 9);
  }

  public int getRandomNumber(Random random, int low, int high) {
    return low + random.nextInt(high - low + 1);
  }

  public void additionQuiz() {
    int numberA = getRandomNumberBetween0And9(this.random);
    int numberB = getRandomNumberBetween0And9(this.random);
    int additionOfTwoNumber = numberA + numberB;

    int userInput = -1;
    while (additionOfTwoNumber != userInput) {
      String questionStr = String.format("%d + %d = ", numberA, numberB);
      userInput = MidEvaluation.requestIntegerNumberInput(questionStr);
      if (additionOfTwoNumber != userInput) {
        out.printf("%d + %d != %d, try again. %n", numberA, numberB, userInput);
      }
    }

    out.printf("            Yes, %d + %d = %d %n", numberA, numberB, userInput);
  }

}
