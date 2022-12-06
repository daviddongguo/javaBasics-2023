package xyz.dongguo.lesson;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;

public class Day04 {

  private static final Random random = new Random();
  private static final int TABLE_WIDTH = 60;
  private static final Scanner scanner = new Scanner(System.in);

  private Day04() {
  }

  public static void calculateNumberUntilQuizzed() {
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

  public static void testNextIntBound() {
    for (int i = 0; i < 500; i++) {
      int n = random.nextInt(101);
      System.out.printf("%4d ", n);
      if (i % 20 == 0) {
        System.out.printf("%n");
      }
      if (n == 0) {
        System.out.printf("%n");
      }
      if (n == 100) {
        System.out.printf("%n");
      }
    }
  }

  public static void guessRandomNumber() {
    int rndNumber = random.nextInt(101);
    guessNumber(rndNumber, System.in, System.out);
  }

  public static void guessNumber(int  expectedNumber, InputStream in, PrintStream out) {
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

}
