package xyz.dongguo;

import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    //    additionQuiz();
    //    calculateYearsOfDoubleTuition();

    subtractionQuiz();
  }

  private static void subtractionQuiz() {
    Scanner scanner = new Scanner(System.in);
    int amountOfCorrect = 0;
    String report = "";
    for (int i = 0; i < 5; i++) {
      int numberA = getRandNumber(0, 9);
      int numberB = getRandNumber(0, 9);
      if (numberA < numberB) {
        int tmp = numberA;
        numberA = numberB;
        numberB = tmp;
      }
      int subtractionOfTwoNumber = numberA - numberB;

      String questionString = String.format("%d)  %d - %d = ", i + 1, numberA, numberB);
      System.out.print(questionString);
      int userInput = scanner.nextInt();

      // log user answer in report string
      if (subtractionOfTwoNumber == userInput) {
        amountOfCorrect++;
        report = report + questionString + userInput + "\t\t\tY\n";
      } else {
        report = report + questionString + userInput + "\t\t\tX\n";
      }
    }

    System.out.printf("You  have %d correct answers.%n", amountOfCorrect);
    System.out.println(report);
    scanner.close();
  }

  private static void calculateYearsOfDoubleTuition() {
    int yearsOfDoubleInitial = 0;
    double initialTuition = 1.0D;
    double increaseRate = 0.07D;
    double currentTuition = initialTuition;

    while (currentTuition < 2 * initialTuition) {
      yearsOfDoubleInitial++;
      currentTuition *= (1 + increaseRate);
    }
    System.out.printf("%n\t\t\tAfter %d years, the tuition will double.%n", yearsOfDoubleInitial);

  }

  private static void additionQuiz() {
    Scanner scanner = new Scanner(System.in);

    int numberA = getRandNumberBetween0And9();
    int numberB = getRandNumberBetween0And9();
    int additionOfTwoNumber = numberA + numberB;

    int userInput = -1;
    while (additionOfTwoNumber != userInput) {
      System.out.printf("%d + %d = ", numberA, numberB);
      userInput = scanner.nextInt();
      if (additionOfTwoNumber != userInput) {
        System.out.printf("%d + %d != %d, try again. %n", numberA, numberB, userInput);
      }
    }

    System.out.printf("            Yes, %d + %d = %d %n", numberA, numberB, userInput);
    scanner.close();
  }

  private static int getRandNumberBetween0And9() {
    return (int) (Math.random() * 10);
  }

  private static int getRandNumber(int a, int b) {
    return a + (int) (Math.random() * (b + 1));
  }
}