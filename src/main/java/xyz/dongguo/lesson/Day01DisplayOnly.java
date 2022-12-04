package xyz.dongguo.lesson;

import java.io.PrintStream;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Demo class
 *
 * @author Dongguo
 * @date 2022/12/02
 */
public class Day01DisplayOnly {

  private final PrintStream out;
  private final ThreadLocalRandom random;
  static final String FLOAT_NUMBER_FORMATTER = "%20.14f";
  static final String SUM_OF_SERIES = "%n\t\t\t0.01 + 0.02 + ... + 1.00 = ";

  public Day01DisplayOnly(PrintStream out) {
    this.out = out;
    this.random = ThreadLocalRandom.current();
  }

  public Random getRandom() {
    return this.random;
  }
  public void printTable(){
    printTable(60);
  }

  public void printTable(int width) {
    printWholeRow("_", width);
    System.out.printf("%n");
    printRowCenter("Name", "VIP", "Balance");
    printWholeRow("-", width);
    System.out.printf("%n");
    printRowCenterContent("Dongguo", true, 1234.56D);
    printRowCenterContent("Anonymous", false, 998855663.21D);
    printRowCenterContent("Rich", false, -88995566.99D);
    printWholeRow("_", width);
    System.out.printf("%n");
  }

  private void printWholeRow(String str, int width) {
    System.out.print(str.repeat(width));
  }

  private void printRowCenterContent(String name, boolean isVIP, double amount) {
    printRowCenter(String.format("%.16s", name),
       String.format("%-5b", isVIP),
       String.format("%+,15.2f", amount));
  }

  private void printRowCenter(String column1, String column2, String column3) {
    int widthOfColumn1 = 16;
    String pad1Left = " ".repeat((widthOfColumn1 - column1.length()) / 2);
    String pad1Right = " ".repeat(widthOfColumn1 - column1.length() - pad1Left.length());
    int widthOfColumn2 = 20;
    String pad2Left = " ".repeat((widthOfColumn2 - column2.length()) / 2);
    String pad2Right = " ".repeat(widthOfColumn2 - column2.length() - pad2Left.length());
    int widthOfColumn3 = 20;
    String pad3Left = " ".repeat((widthOfColumn3 - column3.length()) / 2);
    String pad3Right = " ".repeat(widthOfColumn3 - column3.length() - pad3Left.length());
    System.out.printf("|%s%s%s|%s%s%s|%s%s%s|%n",
       pad1Left, column1, pad1Right,
       pad2Left, column2, pad2Right,
       pad3Left, column3, pad3Right
    );
  }

  public void printSumOfNumberSeries() {
    int sum = 0;
    int size = 100;
    for (int i = 1; i <= size; i++) {
      sum += i;
      out.printf(FLOAT_NUMBER_FORMATTER, (float) sum / 100);
      if ((int) (i * 100 + 0.5) % 10 == 0) {
        out.printf("%n");
      }
    }
    out.printf(SUM_OF_SERIES);
    out.printf(FLOAT_NUMBER_FORMATTER, (float) sum);
    out.printf("%n");
  }

  public void printSumOfDoubleNumberSeries() {
    double sum = 0.0D;
    double initialValue = 0.01D;
    double step = 0.01D;
    double finalValue = 1.0D;
    for (double i = initialValue; i <= finalValue; i += step) {
      sum += i;
      out.printf(FLOAT_NUMBER_FORMATTER, sum);
      if ((int) (i * 100 + 0.5) % 10 == 0) {
        out.printf("%n");
      }
    }
    out.printf(SUM_OF_SERIES);
    out.printf(FLOAT_NUMBER_FORMATTER, sum);
    out.printf("%n");

    sum = 0.0F;
    int sumOfInteger = 0;
    int j = 1;
    for (double i = initialValue; i <= finalValue; i += step) {
      sum += i;
      sumOfInteger += j;
      out.printf(FLOAT_NUMBER_FORMATTER, (sum - Float.parseFloat(String.valueOf(sumOfInteger)) / 100) * 1000000);
      if ((int) (i * 100 + 0.5) % 10 == 0) {
        out.printf("%n");
      }
      j++;
    }
  }

  public void printSumOfFloatNumberSeries() {
    float sum = 0.0F;
    float initialValue = 0.01F;
    float step = 0.01F;
    float finalValue = 1.0F;
    for (float i = initialValue; i <= finalValue; i += step) {
      sum += i;
      out.printf(FLOAT_NUMBER_FORMATTER, sum);
      if ((int) (i * 100 + 0.5) % 10 == 0) {
        out.printf("%n");
      }
    }
    out.printf(SUM_OF_SERIES);
    out.printf(FLOAT_NUMBER_FORMATTER, sum);
    out.printf("%n");

    sum = 0.0F;
    int sumOfInteger = 0;
    int j = 1;
    for (float i = initialValue; i <= finalValue; i += step) {
      sum += i;
      sumOfInteger += j;
      out.printf(FLOAT_NUMBER_FORMATTER, (sum - Float.parseFloat(String.valueOf(sumOfInteger)) / 100) * 1000000);
      if ((int) (i * 100 + 0.5) % 10 == 0) {
        out.printf("%n");
      }
      j++;
    }
  }

  public void printMultiplicationTable(int numberOfTimes) {
    out.printf("%n");
    out.printf("%35s%n", "MULTIPLICATION TABLE");
    out.printf("%n");
    printRowHeader(numberOfTimes);

    for (int indexOfRow = 0; indexOfRow < numberOfTimes; indexOfRow++) {
      out.printf("%2d | ", indexOfRow + 1);
      for (int indexOfColumn = 0; indexOfColumn < numberOfTimes; indexOfColumn++) {
        out.printf("%5d", (indexOfRow + 1) * (indexOfColumn + 1));
      }
      out.printf("%n");
    }
  }

  private void printRowHeader(int numberOfTimes) {
    out.printf("%2s | ", " ");
    for (int j = 0; j < numberOfTimes; j++) {
      out.printf("%5d", j + 1);
    }
    out.printf("%n");
    out.println("-".repeat(5 + numberOfTimes * 5));
  }

  public void printYearsOfDoubleTuition() {
    printYearsAfterTimes(2);

  }

  public void printYearsAfterTimes(int times) {
    int yearsOfDoubleInitial = 0;
    double initialTuition = 1.0D;
    double increaseRate = 0.07D;
    double currentTuition = initialTuition;

    while (currentTuition < times * initialTuition) {
      yearsOfDoubleInitial++;
      currentTuition = initialTuition * calculateCompoundInterest(increaseRate, yearsOfDoubleInitial);
    }
    out.printf("%n\t\t\tAfter %d years, the tuition will double.", yearsOfDoubleInitial);
    out.printf("%n\t\t\tThe Tuition will be %.4f%n", currentTuition);
  }

  public double calculateCompoundInterest(double interest, int numberOfPeriods) {
    return Math.pow((1 + interest), numberOfPeriods);
  }

}



