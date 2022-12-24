package xyz.dongguo.lesson.objectoriented.laddersnakegame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utility {

  private Utility() {
  }

  public static int requestNumberBetweenMinAndMax(int min, int max, Scanner scanner) {
    while (true) {
      int inputNumber = requestIntegerNumberInput(scanner);
      if (inputNumber >= min && inputNumber <= max) {
        return inputNumber;
      }
      System.out.printf("number must be between %d and %d.%n", min, max);
    }
  }

  public static int requestIntegerNumberInput(Scanner scanner) {
    while (true) {
      try {
        return Integer.parseInt(requestStringInput(scanner));
      } catch (NumberFormatException ex) {
        System.out.println("input must be an integer number.");
      }
    }
  }

  private static String requestStringInput(Scanner scanner) {
    while (true) {
      String inputString = scanner.nextLine().trim();
      if (inputString.length() >= 1) {
        return inputString;
      }
      System.out.println("input can not be empty.");
    }
  }

  public static void printHeart() {
    List<String> list = new ArrayList<>(List.of(
       "               ",
       "       *  *    ",
       "    *       *  ",
       "   *          *",
       "    *          ",
       "      *        ",
       "         *     ",
       "            *  ",
       "              *"
    ));
    System.out.printf("%n%n");
    for (String str : list) {
      System.out.printf("%s%s%n", str, new StringBuilder(str).reverse().toString().substring(1));
    }
  }
}
