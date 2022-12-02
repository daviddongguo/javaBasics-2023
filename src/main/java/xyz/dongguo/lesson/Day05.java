package xyz.dongguo.lesson;

import java.util.Random;

public class Day05 {

  private static final Random random = new Random();

  private Day05() {
  }

  public static void run() {
    printYearsOfDoubleTuition();
//    printMultiplicationTable();
//    printSumOfSeries();
//    subtractionQuiz();
//    additionQuiz();
  }

  private static void printSumOfSeries() {
    float sum = 0.0F;
    for (float i = 0.01F; i <= 1.0F; i += 0.01F) {
      sum += i;
      System.out.printf("%.4f\t", sum);
      if ((int) (i * 100 + 0.5) % 20 == 0) {
        System.out.printf("%n");
      }
    }
    System.out.printf("%n\t\t\t0.01 + 0.02 + ... + 1.00 = %.4f%n", sum);
  }

  private static void printMultiplicationTable() {
    final int NUMBER_OF_COLUMN = 9;
    final int NUMBER_OF_ROW = 9;
    System.out.printf("%n");
    System.out.printf("%35s%n", "MULTIPLICATION TABLE");
    System.out.printf("%n");
    printRowHeader();

    for (int indexOfRow = 0; indexOfRow < NUMBER_OF_ROW; indexOfRow++) {
      System.out.printf("%2d | ", indexOfRow + 1);
      for (int indexOfColumn = 0; indexOfColumn < NUMBER_OF_COLUMN; indexOfColumn++) {
        System.out.printf("%5d", (indexOfRow + 1) * (indexOfColumn + 1));
      }
      System.out.printf("%n");
    }
  }

  private static void printRowHeader() {
    final int NUMBER_OF_COLUMN = 9;
    System.out.printf("%2s | ", " ");
    for (int j = 0; j < NUMBER_OF_COLUMN; j++) {
      System.out.printf("%5d", j + 1);
    }
    System.out.printf("%n");
    System.out.println("-".repeat(5 + NUMBER_OF_COLUMN * 5));
  }

  private static void printYearsOfDoubleTuition() {
    int yearsOfDoubleInitial = 0;
    double initialTuition = 1.0D;
    double increaseRate = 0.07D;
    double currentTuition = initialTuition;

    while (currentTuition < 2 * initialTuition) {
      yearsOfDoubleInitial++;
      currentTuition = initialTuition * calculateCompoundInterest(increaseRate, yearsOfDoubleInitial);
    }
    System.out.printf("%n\t\t\tAfter %d years, the tuition will double.", yearsOfDoubleInitial);
    System.out.printf("%n\t\t\tThe Tuition will be %.4f%n", currentTuition);
  }

  public static double calculateCompoundInterest(double interest, int numberOfPeriods) {
    return Math.pow((1 + interest), numberOfPeriods);
  }


  private static void subtractionQuiz() {
    final int NUMBER_OF_QUESTIONS = 1;
    int amountOfCorrect = 0;
    StringBuilder report = new StringBuilder();
    for (int i = 0; i < NUMBER_OF_QUESTIONS; i++) {
      int numberA = getRandomNumberBetween0And9();
      int numberB = getRandomNumberBetween0And9();
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

    System.out.printf("You  have %d correct answers.%n", amountOfCorrect);
    System.out.println(report);
  }

  public static int getRandomNumberBetween0And9() {
    //    a + (int) (Math.random() * (b - a + 1));
    return Day05.random.nextInt(9 + 1);
  }

  private static void additionQuiz() {

    int numberA = getRandomNumberBetween0And9();
    int numberB = getRandomNumberBetween0And9();
    int additionOfTwoNumber = numberA + numberB;

    int userInput = -1;
    while (additionOfTwoNumber != userInput) {
      String questionStr = String.format("%d + %d = ", numberA, numberB);
      userInput = xyz.dongguo.lesson.MidEvaluation.requestIntegerNumberInput(questionStr);
      if (additionOfTwoNumber != userInput) {
        System.out.printf("%d + %d != %d, try again. %n", numberA, numberB, userInput);
      }
    }

    System.out.printf("            Yes, %d + %d = %d %n", numberA, numberB, userInput);
  }

}
