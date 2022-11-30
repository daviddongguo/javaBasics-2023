package xyz.dongguo;

import java.util.Scanner;

public class Main {

  private static final Scanner scanner = new Scanner(System.in);
  private static final int CONSOLE_WIDTH = 50;

  public static void main(String[] args) {
    //    additionQuiz();
    //    calculateYearsOfDoubleTuition();
    //    subtractionQuiz();
    //    printMultiplicationTable();
    //    subtractionQuiz();
    printSumOfSeries();

  }

  private static void printSumOfSeries() {
    float sum = 0.0F;
    for (int i = 1; i <= 100; i++) {
      float subSum = 0.01F * i;

//      for (int indexInner = 0; indexInner <= indexOuter; indexInner++) {
//        subSum += 0.01;
//      }
      sum += subSum;

      System.out.printf("%4.2f\t", subSum);
      if(i % 20 == 0){
        System.out.printf("%n");
      }
    }
    System.out.printf("%n\t\t\t0.01 + 0.02 + ... + 1.00 = %4.2f%n%n", sum);
  }

  private static void printMultiplicationTable() {
    final int NUMBER_COLUMN = 9;
    final int NUMBER_ROW = 9;
    System.out.printf("%n");
    System.out.printf("%35s%n", "MULTIPLICATION TABLE");
    System.out.printf("%n");
    printRowHeader(NUMBER_COLUMN);

    for (int indexOfRow = 0; indexOfRow < NUMBER_ROW; indexOfRow++) {
      System.out.printf("%2d | ", indexOfRow + 1);
      for (int indexOfColumn = 0; indexOfColumn < NUMBER_COLUMN; indexOfColumn++) {
        System.out.printf("%5d", (indexOfRow + 1) * (indexOfColumn + 1));
      }
      System.out.printf("%n");
    }
  }

  private static void printRowHeader(int numberOfColumn) {
    System.out.printf("%2s | ", " ");
    for (int j = 0; j < numberOfColumn; j++) {
      System.out.printf("%5d", j + 1);
    }
    System.out.printf("%n");
    System.out.println("-".repeat(5 + numberOfColumn * 5));
  }

  private static void subtractionQuiz() {
    int amountOfCorrect = 0;
    StringBuilder report = new StringBuilder();
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
      //      System.out.print(questionString);
      //      int userInput = scanner.nextInt();
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

    System.out.printf("You  have %d correct answers.%n", amountOfCorrect);
    System.out.println(report.toString());
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
    return getRandNumber(0, 9);
  }

  private static int getRandNumber(int a, int b) {
    return a + (int) (Math.random() * (b - a + 1));
  }
}