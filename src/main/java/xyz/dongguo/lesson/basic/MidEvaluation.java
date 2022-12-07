package xyz.dongguo.lesson.basic;

import static java.lang.System.in;
import static java.lang.System.out;

import java.util.Scanner;

/**
 * @author dongg
 */
public class MidEvaluation {

  private static final int CONSOLE_WIDTH = 50;
  private static final Scanner SCANNER = new Scanner(in);

  private MidEvaluation() {
  }

  public static void run() {
    println("*".repeat(CONSOLE_WIDTH));
    printlnCenter("Question 1 Current Convert");
    question1();
    pressEnterKeyToContinue();
    printlnCenter("Question 2 Palindrome");
    question2();
    pressEnterKeyToContinue();
    printlnCenter("Question 3 Hex Digit Convert");
    question3();
    pressEnterKeyToContinue();
    printlnCenter("Question 4 Display Days of Month");
    question4();
    println("*".repeat(CONSOLE_WIDTH));
  }

  private static void question1() {
    int exchangeRate;
    int dollarAmountByCent;
    int yuanAmountByCent;
    boolean isYuanToDollar;

    exchangeRate = (int) (requestFloatNumberInput("Enter the exchange rate from dollars to Yuan: ") * 100);
    isYuanToDollar = requestCovertDirection("Enter 0 to convert dollars to Yuan and 1 vice versa: ");

    if (isYuanToDollar) {
      yuanAmountByCent = (int) (requestFloatNumberInput("Enter the Yuan amount: ") * 100);
      String resultStr = String.format("%.2f Yuan is $%.2f", ((float) yuanAmountByCent) / 100,
         yuanAmountByCent / (float) exchangeRate);
      println(resultStr);
    } else {
      dollarAmountByCent = (int) (requestFloatNumberInput("Enter the dollar amount: ") * 100);
      String resultStr = String.format("$%.2f is %.2f Yuan", ((float) dollarAmountByCent) / 100,
         (dollarAmountByCent * exchangeRate) / 10000.0F);
      println(resultStr);
    }
  }

  private static float requestFloatNumberInput(String infoMessage) {
    while (true) {
      try {
        return Float.parseFloat(requestStringInput(infoMessage));
      } catch (NumberFormatException ex) {
        printError("input must be a number.");
      }
    }
  }

  public static boolean requestCovertDirection(String infoMessage) {
    while (true) {
      print(infoMessage);
      String inputStr = SCANNER.nextLine();
      if (inputStr.trim().charAt(0) == '1') {
        return true;
      } else if (inputStr.trim().charAt(0) == '0') {
        return false;
      }
      printError("input must be 0 or 1.");
    }
  }

  private static void question2() {
    int inputNumber;
    boolean isPalindrome = false;
    inputNumber = requestThreeDigitIntegerNumber("Enter a three-digit integer: ");
    final int divisor10 = 10;
    final int divisor100 = 100;
    if (inputNumber % divisor10 == inputNumber / divisor100) {
      isPalindrome = true;
    }
    if (isPalindrome) {
      println(String.format("%3d is a palindrome", inputNumber));
    } else {
      println(String.format("%3d is not a palindrome", inputNumber));
    }
  }

  public static int requestThreeDigitIntegerNumber(String infoMessage) {
    while (true) {
      int inputNumber = requestIntegerNumberInput(infoMessage);
      if (inputNumber >= 100 && inputNumber <= 999) {
        return inputNumber;
      }
      printError("number must be three-digit.");
    }
  }

  private static void question3() {
    char hexNumber = requestHexDigitCharInput("Enter a Hex number: ");
    println(String.valueOf(convertHexNumber(hexNumber)));
  }

  public static char requestHexDigitCharInput(String infoMessage) {
    while (true) {
      String inputStr = requestStringInput(infoMessage);
      char inputChar = inputStr.trim().toUpperCase().charAt(0);
      boolean isHex = (inputChar >= 48 && inputChar <= 57) || (inputChar >= 65 && inputChar <= 70);
      if (isHex) {
        return inputChar;
      } else {
        println("hex digit number must be 0-9 or A-F");
      }
    }
  }

  private static int convertHexNumber(char hexNumber) {
    boolean isNumber = hexNumber >= '0' && hexNumber <= '9';
    if (isNumber) {
      return hexNumber - 48;
    }
    return hexNumber - 'A' + 10;
  }

  private static void question4() {
    int year;
    int indexOfMonth;
    String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    int[] daysOfMonths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    year = requestIntegerNumberInput("Enter a year: ");
    indexOfMonth = requestMonthInput(months, "Enter a month: ");

    if (isLeapYear(year)) {
      daysOfMonths[1] = 29;
    }

    println(String.format("%s %d has %d days", months[indexOfMonth], year, daysOfMonths[indexOfMonth]));
  }

  public static int requestMonthInput(String[] months, String infoMessage) {
    while (true) {
      print(infoMessage);
      String inputMonthStr = requestStringInput(infoMessage);
      inputMonthStr = inputMonthStr.substring(0, Math.min(3, inputMonthStr.length()));
      for (int i = 0; i < months.length; i++) {
        if (months[i].equalsIgnoreCase(inputMonthStr)) {
          return i;
        }
      }
      printError(String.format("%s is not a valid month.", inputMonthStr));
    }
  }

  private static boolean isLeapYear(int year) {

    /*
     False: 1900, 2100, 2200
     True: 2000, 2400
     + 4; - 100; + 400
     */
    return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
  }

  public static int requestIntegerNumberInput(String infoMessage) {
    while (true) {
      try {
        return Integer.parseInt(requestStringInput(infoMessage));
      } catch (NumberFormatException ex) {
        printError("input must be an integer number.");
      }
    }
  }

  private static String requestStringInput(String infoMessage) {
    while (true) {
      print(infoMessage);
      String inputString = SCANNER.nextLine().trim();
      if (inputString.length() >= 1) {
        return inputString;
      }
      printError("input can not be empty.");
    }
  }

  private static void print(String string) {
    out.print(string);
  }

  private static void println(String string) {
    out.println(string);
  }

  private static void printlnCenter(String string) {
    int leftPad = CONSOLE_WIDTH / 2 - string.length() / 2;
    printlnWithPad(string, leftPad);
  }

  private static void printError(String errorMessage) {
    int leftPad = CONSOLE_WIDTH - errorMessage.length();
    printlnWithPad(errorMessage, leftPad);
  }

  private static void printlnWithPad(String string, int leftPad) {
    final String emptyChar = " ";
    println(emptyChar.repeat(Math.max(-1, leftPad)) + string);
  }

  public static void pressEnterKeyToContinue() {
    println("Press Enter key to continue...");
    SCANNER.nextLine();
  }
}
