package xyz.dongguo.lesson;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;

/**
 * Demo class
 *
 * @author Dongguo
 * @date 2022/12/02
 */
public class Day05Quiz {

  private final PrintStream out;
  private final Scanner scanner;
  private final Random random;

  public Day05Quiz(PrintStream out, InputStream in) {
    this.out = out;
    this.scanner = new Scanner(in);
    this.random = new Random();
  }

  public Random getRandom() {
    return this.random;
  }

  public void printInput() {
    out.printf("%nPlease enter any string: ");
    out.println(scanner.nextLine());
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
      int userInput = xyz.dongguo.lesson.MidEvaluation.requestIntegerNumberInput(questionString);

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
      userInput = xyz.dongguo.lesson.MidEvaluation.requestIntegerNumberInput(questionStr);
      if (additionOfTwoNumber != userInput) {
        out.printf("%d + %d != %d, try again. %n", numberA, numberB, userInput);
      }
    }

    out.printf("            Yes, %d + %d = %d %n", numberA, numberB, userInput);
  }

}
