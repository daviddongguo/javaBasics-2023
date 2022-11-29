package xyz.dongguo.lesson;

import java.util.Scanner;

public class Day04 {

  public static void caculateNumberUntilZeo() {
    Scanner scanner = new Scanner(System.in);
    int inputNumber = 0;
    int sum = 0;
    // 1st: False || True
    // 2st: True
    // end: False || False
    while(inputNumber != 0 || sum ==0){
      System.out.print("Enter an integer number to calculate(0 for quit) :");
      inputNumber = scanner.nextInt();
      sum += inputNumber;
    }
    System.out.printf("The sum is %d%n", sum);
  }

  public static void guessNumber() {
    Scanner scanner = new Scanner(System.in);
    int rndNumber = (int) (Math.random() * 101);
    System.out.printf("rand number is %d%n", rndNumber);
    int inputNumber = -1;
    System.out.print("Please enter a number between 0 and 100: ");
    inputNumber = scanner.nextInt();
    while (true) {
      if (rndNumber == inputNumber) {
        System.out.printf("You get it: %d", rndNumber);
        break;
      }
      if (rndNumber < inputNumber) {
        System.out.println("too high");
      } else {
        System.out.println("too low");
      }
      System.out.print("Please enter a new  number: ");
      inputNumber = scanner.nextInt();
    }
  }

  public static void printTable() {
    final int TABLE_WIDTH = 60;
    System.out.printf("_".repeat(TABLE_WIDTH));
    System.out.printf("%n");
    printRowCenter("Name", "VIP", "Balance");
    System.out.printf("-".repeat(TABLE_WIDTH));
    System.out.printf("%n");
    printRowCenterContent("Dongguo", true, 1234.56D);
    printRowCenterContent("Anonymous", false, 998855663.21D);
    printRowCenterContent("Rich", false, -88995566.99D);
    System.out.printf("-".repeat(TABLE_WIDTH));
    System.out.printf("%n");
  }

  private static void printRowCenterContent(String name, boolean isVIP, double amount) {
    printRowCenter(String.format("%.16s", name),
       String.format("%-5b",  isVIP),
       String.format("%+,15.2f", amount));
  }

  private static void printRowCenter(String column1, String column2, String column3) {
    int widthOfColumn1 = 16;
    String pad1Left = " ".repeat((widthOfColumn1 - column1.length()) / 2);
    String pad1Right = " ".repeat(widthOfColumn1 - column1.length() - pad1Left.length()) ;
    int widthOfColumn2 = 20;
    String pad2Left = " ".repeat((widthOfColumn2 - column2.length()) / 2);
    String pad2Right = " ".repeat(widthOfColumn2 - column2.length() - pad2Left.length()) ;
    int widthOfColumn3 = 20;
    String pad3Left = " ".repeat((widthOfColumn3 - column3.length()) / 2);
    String pad3Right = " ".repeat(widthOfColumn3 - column3.length() - pad3Left.length()) ;
    System.out.printf("|%s%s%s|%s%s%s|%s%s%s|%n",
       pad1Left, column1, pad1Right,
       pad2Left, column2, pad2Right,
       pad3Left, column3, pad3Right
    );
  }

  private Day04(){}
}
