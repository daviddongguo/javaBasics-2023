package xyz.dongguo.lesson.objectoriented.laddersnakegame;

import java.util.Scanner;

public class MagicDice implements IEarnable {

  Scanner scanner;

  public MagicDice(Scanner scanner) {
    this.scanner = scanner;
  }

  public int earnScore() {
    System.out.printf("I'm Magic Dice, tell me which number do you love(1-%d): ", Setting.MAX_VALUE_OF_DICE);
    return Utility.requestNumberBetweenMinAndMax(1, Setting.MAX_VALUE_OF_DICE, scanner);
  }

}
