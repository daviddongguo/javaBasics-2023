package xyz.dongguo.lesson.objectoriented.laddersnakegame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomDice implements IEarnable {

  Random random;

  public RandomDice(Random random) {
    this.random = random;
  }

  @Override
  public int earnScore() {
    int minDice = 1;
    int maxDice = Setting.MAX_VALUE_OF_DICE;
    int result = minDice + random.nextInt(maxDice);
    displayDice(result);
    return result;
  }

  /**
   * Just display No other effect
   *
   * @param diceValue a number that will be printed in the end
   */
  private void displayDice(int diceValue) {
    long pauseTime = Setting.GAME_SPEED;
    List<String> template = new ArrayList<>(List.of(
       "                                                                   ",
       "    *  * *                                                         ",
       "  *         *                                                      ",
       "*              *               * *                                 ",
       "                 *           *     *                               ",
       "                   *       *         *      *   *                  ",
       "                     *   *             *   *      *    *           ",
       "                       *                 *         *      *  * * * *"
    ));

    for (int indexOfRow = 0; indexOfRow < template.size(); indexOfRow++) {
      String string = template.get(indexOfRow);
      char[] charArray = string.toCharArray();
      for (int indexOfColumn = 0; indexOfColumn < charArray.length; indexOfColumn++) {
        if (charArray[indexOfColumn] == '*') {
          charArray[indexOfColumn] = (char) (49 + generateRandomUsingDice());
        }
      }
      boolean isLastLine = indexOfRow == template.size() - 1;
      if (isLastLine) {
        String lastLine = String.copyValueOf(charArray);
        System.out.printf("%s ", lastLine.substring(0, 64));
        pauseDisplay(pauseTime * 2);
        System.out.printf("%s ", lastLine.charAt(65));
        pauseDisplay(pauseTime * 3);
        System.out.printf("%s ", lastLine.charAt(67));
        pauseDisplay(pauseTime * 3);
        System.out.printf("%s%n", diceValue);
        pauseDisplay(pauseTime * 3);
      } else {
        System.out.println(String.copyValueOf(charArray));
      }
    }
  }

  /**
   * No display effect
   *
   * @return 1, 2, ... 6
   */
  private int generateRandomUsingDice() {
    int minDice = 1;
    int maxDice = Setting.MAX_VALUE_OF_DICE;
    return minDice + random.nextInt(maxDice);
  }

  /**
   * Pause the display
   *
   * @param millis a time by millions
   */
  private void pauseDisplay(Long millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException ex) {
      System.err.println(ex.getMessage());
    }
  }
}
