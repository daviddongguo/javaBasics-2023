package xyz.dongguo.lesson;

import java.util.Random;
import xyz.dongguo.utility.ConsoleOutput;

/**
 * @author dongguo
 */
public class Day03 {

  private static final Random RANDOM = new Random();

  private Day03() {
  }

  public static void printRandomHiFive() {
    int randomNumber = RANDOM.nextInt(99);
    String message = "";
    final int divisorFive = 5;
    final int divisorEven = 2;
    if (randomNumber % divisorFive == 0) {
      message = "HiFive";
    } else if (randomNumber % divisorEven == 0) {
      message = "HiEven";
    }

    ConsoleOutput.log(String.format("%d is %s", randomNumber, message));
  }

}