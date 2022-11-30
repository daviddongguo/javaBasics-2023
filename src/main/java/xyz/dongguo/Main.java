package xyz.dongguo;

import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    additionQuiz();

  }

  private static void additionQuiz() {
    Scanner scanner = new Scanner(System.in);

    int numberA = 10 + (int)(Math.random() * 91);
    int numberB = 10 + (int)(Math.random() * 91);
    int result = numberA + numberB;

    System.out.printf("%d + %d = ", numberA, numberB);
    int userInput = scanner.nextInt();
    while(result != userInput){
      System.out.printf("%d + %d != %d, try again. %n", numberA, numberB, userInput);
      System.out.printf("%d + %d = ", numberA, numberB);
      userInput = scanner.nextInt();
    }
    System.out.printf("Yes, %d + %d = %d %n", numberA, numberB, userInput);
    scanner.close();
  }
}