import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

/**
 * Run successfully
 *
 * @author Mueataz Qasem Qasem, Dongguo
 * @version 1.2 {@code @date} 2022-12-19 21:58
 */
public class LadderAndSnake {

  private static final Queue<Player> playerQueue = new LinkedList<>();
  private static final List<Player> playerListSortedByPosition = new ArrayList<>(4);
  private static final HashMap<Integer, Integer> ladderAndSnakePosition = new HashMap<>();
  private static final char[] boardDesign = new char[Setting.BROAD_SIZE + 1];
  private static final Random oneRandomToReuse = new Random();
  private static final Scanner oneKeyboardInputNeedToClose = new Scanner(System.in);

  public static void main(String[] args) {
    initializeLadderAndSnakePosition();
    initializeBoard();
    displayBoard();
    //    List<Player> list = welcomePlayers();
    List<Player> list = mockWelcomePlayers();
    initializePlayer(list);
    play();

    oneKeyboardInputNeedToClose.close();
    System.out.println("\n\nBye");
    System.exit(0);
  }
  /**
   * Display the game board that shows the position of players and ladder, snake.
   */
  private static void displayBoard() {
    List<String> list = getLadderAndSnakeBoard();
    for (String s : list) {
      System.out.println(s);
    }
    pauseGame("Welcome to Ladder And Snake Game.");
  }
  /**
   * Add players and decide their order
   *
   * @return a list of Players who will take part in the game
   * rename partTwo to something else
   */
  private static List<Player> welcomePlayers() {

    List<Player> playerList = partTwo();

    //TODO: the second step is to "Now deciding which player will start playing"
    List<Player> finalList = new ArrayList<>();
    decideOrderOfStart(finalList, playerList);

    return finalList;
  }
  /**
   *
   * raceOrderOfStart---> decideOrderOfStart
   */
  private static void decideOrderOfStart(List<Player> finalList, List<Player> listToDecide) {
    if (listToDecide.size() == 1) {
      finalList.add(listToDecide.get(0));
    }

    HashMap<Integer, List<Player>> map = new HashMap<>();
    for (Player player : listToDecide) {
      var list = map.get(player.diceValue);
      if (list == null) {
        list = new ArrayList<>(List.of(player));
        map.put(player.diceValue, list);
      } else {
        list.add(player);
      }
    }

    for (int i = 6; i >= 0; i--) {
      List<Player> list = map.get(i);
      if (list == null) {
        continue;
      }
      if (list.size() == 1) {
        finalList.add(list.get(0));
      }
      if (list.size() >= 2) {
        for (Player currentPlayer : list) {
          int currentDice;
          if (Setting.DEVELOPMENT_MODE) {
            System.out.println("\n===========Development  Mode======================");
            System.out.printf("%s, please enter 1-6 to get your dice value directly:  ", currentPlayer.name);
            currentDice = oneKeyboardInputNeedToClose.nextInt();

          } else {
            currentDice = flipDice();
            currentPlayer.diceValue = currentDice;
          }
          System.out.printf("%s get a dice value of %d %n", currentPlayer.name, currentDice);
        }
        decideOrderOfStart(finalList, list);
      }
    }

  }

  private static List<Player> mockWelcomePlayers() {
    return new LinkedList<>(
       List.of(new Player("Player 01", 2),
          new Player("Player 02", 1),
          new Player("Player 03", 0),
          new Player("Player 04", 3)));
  }

  private static void qasemWelcomePlayers() { // determine the order of playing turns
    List<Player> playerList = partTwo();
    int numPlayers = 2;
    int[] diceRolls = new int[numPlayers];
    Scanner input = oneKeyboardInputNeedToClose;
    while (true) {
      // find the player with the highest dice roll value
      int maxDiceRoll = 0;
      int maxDiceRollIndex = 0;
      for (int i = 0; i < numPlayers; i++) {
        if (diceRolls[i] > maxDiceRoll) {
          maxDiceRoll = diceRolls[i];
          maxDiceRollIndex = i;
        }
      }
      boolean tieExists = false;
      for (int i = 0; i < numPlayers; i++) {
        if (i != maxDiceRollIndex && diceRolls[i] == maxDiceRoll) {
          tieExists = true;
          break;
        }
      }
      if (!tieExists) {
        playerList.get(maxDiceRollIndex).setOrderOfStart(0);
        for (int i = 1; i < numPlayers; i++) {
          int nextPlayerIndex = (maxDiceRollIndex + i) % numPlayers;
          playerList.get(nextPlayerIndex).setOrderOfStart(i);
        }
      }

      // if there are ties, re-roll the dice for the players with the tied dice roll values
      // and continue the loop until there are no more ties
      else {
        List<Integer> tiedPlayerIndices = new ArrayList<>();
        for (int j = 0; j < numPlayers; j++) {
          if (j != maxDiceRollIndex && diceRolls[j] == maxDiceRoll) {
            tiedPlayerIndices.add(j);
          }
        }
        for (int tiedPlayerIndex : tiedPlayerIndices) {
          System.out.println(playerList.get(tiedPlayerIndex).name + ", press enter to re-roll the dice:");
          input.nextLine();  // consume newline character
          //          diceRolls[tiedPlayerIndex] = Player.rollDice();  // re-roll the dice
          diceRolls[tiedPlayerIndex] = flipDice();  // re-roll the dice
          System.out.println(playerList.get(tiedPlayerIndex).name + " rolled a " + diceRolls[tiedPlayerIndex]);
        }

      }
      break;
    }
  }

  /**
   * Include display effect
   *
   * @return 1, 2, ... 6
   */
  private static int flipDice() {
    int result = generateRandomBetween1And6();
    displayDice(result);
    return result;
  }

  /**
   * No display effect
   *
   * @return 1, 2, ... 6
   */
  private static int generateRandomBetween1And6() {
    int minDice = 1;
    int maxDice = 6;
    return minDice + oneRandomToReuse.nextInt(maxDice);
  }

  /**
   * Just display No other effect
   *
   * @param diceValue a number that will be printed in the end
   */
  private static void displayDice(int diceValue) {
    long pauseTime = Setting.GAME_SPEED;
    List<String> template = new ArrayList<>(List.of(
       "       *                                                           ",
       "    *    *                                                         ",
       "  *         *                                                      ",
       "*              *               * *                                 ",
       "                 *           *     *          *                    ",
       "                   *       *         *      *   *      *           ",
       "                     *   *             *   *      *  *   * *       ",
       "                       *                 *         *      *  * *  "
    ));

    for (int indexOfRow = 0; indexOfRow < template.size(); indexOfRow++) {
      String string = template.get(indexOfRow);
      char[] charArray = string.toCharArray();
      for (int indexOfColumn = 0; indexOfColumn < charArray.length; indexOfColumn++) {
        if (charArray[indexOfColumn] == '*') {
          charArray[indexOfColumn] = (char) (49 + generateRandomBetween1And6());
        }
      }
      boolean isLastLine = indexOfRow == template.size() - 1;
      if (isLastLine) {
        System.out.printf("%s ", String.copyValueOf(charArray));
        pauseDisplay(pauseTime * 2);
        System.out.printf("%s%n", diceValue);
      } else {
        System.out.println(String.copyValueOf(charArray));
      }
    }
  }

  /**
   * Pause the display
   *
   * @param millis a time by millions
   */
  private static void pauseDisplay(Long millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException ex) {
      System.err.println(ex.getMessage());
    }
  }

  private static List<Player> partTwo() {
    List<Player> playerList = new ArrayList<>();
    //TODO: reuse one scanner in the whole project
    Scanner input = oneKeyboardInputNeedToClose;
    //    Scanner input = new Scanner(System.in);
    System.out.println("Enter A Number of players");
    int numPlayers = input.nextInt();

    // decide number of players (must be between 2 and 4)
    //TODO: simple the logic test
    while (numPlayers != 2
       && numPlayers != 3
       && numPlayers != 4) {
      //    while (numPlayers < 2 || numPlayers > 4) {
      System.out.println("Number of players must be between 2 and 4 ");
      numPlayers = input.nextInt();
    }

    //TODO: two steps, the first step is to set player name
    //    int[] diceRolls = new int[numPlayers];
    for (int i = 0; i < numPlayers; i++) {
      System.out.println("Enter the name of player " + (i + 1) + ":");
      String playerName = input.next();
      Player player = new Player(playerName);
      System.out.println(playerName + ", press enter to roll the dice:");
      input.nextLine();  // consume newline character

      //      diceRolls[i] = flipDice();  // roll the dice
      //      System.out.println(playerName + " rolled a " + diceRolls[i]);
      playerList.add(player);
    }
    return playerList;
  }

  /**
   * Order the players by the orderOfStart property <p />Add the sorted players into the playerQueueInPlaying and
   * playerListSortedByPosition
   */
  private static void initializePlayer(List<Player> list) {
    list.sort((a, b) -> a.orderOfStart.getIndex() - b.orderOfStart.getIndex());
    playerQueue.addAll(list);
    playerListSortedByPosition.addAll(list);
    displayPlayers(null);
  }

  private static void play() {
    if (playerQueue.isEmpty()) {
      System.out.println("No player is ready.");
      return;
    }

    Player currentPlayer;
    while (true) {
      currentPlayer = playerQueue.poll();
      assert currentPlayer != null;

      currentPlayer.position = go(currentPlayer);
      playerQueue.add(currentPlayer);

      displayPlayers(playerQueue.peek());
      pauseGame("Please press Enter to continue");
      if (currentPlayer.position >= Setting.BROAD_SIZE) {
        System.out.printf("%s wins.", currentPlayer.name);
        return;
      }

    }
  }

  /**
   * move based on a random number create by flip dice and on the rules of the game broad
   *
   * @param currentPlayer a player who is moving
   * @return the position where the player will go
   */
  private static int go(Player currentPlayer) {
    int position = currentPlayer.position;
    System.out.printf("%n%s(position=%d) :  go....\t", currentPlayer.name, currentPlayer.position);
    pauseGame("(Press Enter to flip dice)");

    int currentDice;
    if (Setting.DEVELOPMENT_MODE) {
      System.out.println("\n===========Development  Mode======================");
      System.out.printf("%s, please enter 1-6 to get your dice value directly:  ", currentPlayer.name);
      currentDice = oneKeyboardInputNeedToClose.nextInt();

    } else {
      currentDice = flipDice();
    }

    System.out.println("got a dice value of " + currentDice);
    System.out.printf("move to %d = %d + %d%n", position + currentDice, position, currentDice);
    position += currentDice;
    pauseGame("Please press Enter to continue");
    while (ladderAndSnakePosition.containsKey(position)) {
      System.out.println("then...");
      System.out.println("continue move automatically");

      int newPosition = ladderAndSnakePosition.get(position);
      if (newPosition > position) {
        System.out.println("LADDER  " + "=|".repeat((newPosition - position) * 3));
      } else {
        System.out.println("SNAKE    " + "~".repeat((position - newPosition) * 3));
      }
      position = newPosition;
      System.out.println("move to " + position);
      displayPlayers(currentPlayer);
      pauseGame("Please press Enter to continue");
    }

    return position;
  }

  private static void pauseGame(String message) {
    if (Setting.AUTO_RUN == false) {
      System.out.printf(String.format("%s", message));
      oneKeyboardInputNeedToClose.nextLine();
    }
  }

  /**
   * Dongguo is working Display the player information.
   */
  private static void displayPlayers(Player currentPlayer) {
    List<String> ladderAndSnakeBoard = getLadderAndSnakeBoard();
    List<String> playerScoreInfo = getPlayerScoreInfo(currentPlayer);
    System.out.printf("%n%n%n");
    final int scoresInfoPosition = 3;
    final int scoresInfoHeight = playerScoreInfo.size();
    for (int i = 0; i < ladderAndSnakeBoard.size(); i++) {
      if (i >= scoresInfoPosition && i < scoresInfoPosition + scoresInfoHeight) {
        int j = i - scoresInfoPosition;
        System.out.println(ladderAndSnakeBoard.get(i) + "\t\t" + playerScoreInfo.get(j));
      } else {
        System.out.println(ladderAndSnakeBoard.get(i));
      }
    }
  }

  private static char[] initializeBoard() {
    char[] chars = new char[101];
    char charSnake = 'ㄹ';
    char charLadder = 'ㅒ';
    char charSquare = 'ㆍ';
    char charFinal = 'ㅇ';
    char charStart = 'ㅿ';

    Arrays.fill(chars, charSquare);
    chars[1] = charStart;
    chars[100] = charFinal;
    fillSnakeAndLadder(charSnake, charLadder, chars);
    return chars;
  }

  /**
   * the game board that include the position ladder and snake.
   * and the position of players.
   * @return  a string list
   */
  private static List<String> getLadderAndSnakeBoard() {
    List<String> list = new ArrayList<>(12);

    System.arraycopy(initializeBoard(), 0, boardDesign, 0, Setting.BROAD_SIZE + 1);
    fillPlayerPosition(boardDesign);

    // Table Header
    char[] romanNumbers = {'Ⅰ', 'Ⅱ', 'Ⅲ', 'Ⅳ', 'Ⅴ', 'Ⅵ', 'Ⅶ', 'Ⅷ', 'Ⅸ', 'Ⅹ'};
    StringBuilder stringToAddInList = new StringBuilder(" ");
    for (int i = romanNumbers.length - 1; i >= 0; i--) {
      stringToAddInList.append(String.format(" %s", romanNumbers[i]));
    }
    list.add(stringToAddInList.toString());

    // Table Body
    for (int i = 0; i <= 9; i++) {
      stringToAddInList = new StringBuilder();
      if (i % 2 == 1) {
        stringToAddInList.append("").append(9 - i).append(" ");
        for (int j = 1; j <= 10; j++) {
          int indexOfMap = (9 - i) * 10 + j;
          stringToAddInList.append(boardDesign[indexOfMap]).append(" ");
        }
        stringToAddInList.append("  ");
      } else {
        stringToAddInList.append("  ");
        for (int j = 1; j <= 10; j++) {
          int indexOfMap = (9 - i) * 10 - j + 11;
          stringToAddInList.append(boardDesign[indexOfMap]).append(" ");
        }
        stringToAddInList.append("").append(9 - i).append(" ");
      }
      list.add(stringToAddInList.toString());
    }

    // Bottom
    stringToAddInList = new StringBuilder(" ");
    for (char romanNumberal : romanNumbers) {
      stringToAddInList.append(String.format(" %s", romanNumberal));
    }
    list.add(stringToAddInList.toString());

    return list;
  }

  private static void fillPlayerPosition(char[] boardRecord) {
    for (Player player : playerListSortedByPosition) {
      boardRecord[player.position] = player.orderOfStart.getChar();
    }
  }

  private static void fillSnakeAndLadder(char charSnaker, char charLadder, char[] boardRecord) {
    for (Map.Entry<Integer, Integer> entry : ladderAndSnakePosition.entrySet()) {
      int position = entry.getKey();
      int positionToMove = entry.getValue();
      if (position < 100 && positionToMove > position) {
        boardRecord[position] = charLadder;
      }
      if (position < 100 && positionToMove < position) {
        boardRecord[position] = charSnaker;
      }
    }
  }

  /**
   * Live Scores
   *
   * @return a list of string showing the players ordered by their position
   */
  private static List<String> getPlayerScoreInfo(Player currentPlayer) {
    List<String> list = new ArrayList<>(5);
    playerListSortedByPosition.sort((a, b) -> b.position - a.position);
    for (Player player : playerListSortedByPosition) {
      boolean isCurrentPlayer = false;
      if (currentPlayer != null) {
        isCurrentPlayer = player.name.equalsIgnoreCase(currentPlayer.name);
      }
      list.add(
         String.format("%4s %c %-16s now in square: %3d", isCurrentPlayer ? "->" : "  ",
            player.orderOfStart.getChar(),
            player.name, player.position));
    }

    return list;
  }

  private static void disPlayPlayerPosition(Player player, boolean isCurrentPlayer) {
    System.out.printf("%4s %c %-16.15s now in square: %3d%n", isCurrentPlayer ? "->" : "  ",
       player.orderOfStart.getChar(),
       player.name, player.position);
  }

  /**
   * set all ladder and snake rules and moving backward rules when exceeding maximum
   */
  private static void initializeLadderAndSnakePosition() {
    ladderAndSnakePosition.put(105, 95);
    ladderAndSnakePosition.put(104, 96);
    ladderAndSnakePosition.put(103, 97);
    ladderAndSnakePosition.put(102, 98);
    ladderAndSnakePosition.put(101, 99);
    ladderAndSnakePosition.put(98, 78);
    ladderAndSnakePosition.put(97, 76);
    ladderAndSnakePosition.put(95, 24);
    ladderAndSnakePosition.put(93, 68);
    ladderAndSnakePosition.put(80, 100);
    ladderAndSnakePosition.put(79, 19);
    ladderAndSnakePosition.put(71, 91);
    ladderAndSnakePosition.put(64, 60);
    ladderAndSnakePosition.put(51, 67);
    ladderAndSnakePosition.put(48, 30);
    ladderAndSnakePosition.put(36, 44);
    ladderAndSnakePosition.put(28, 84);
    ladderAndSnakePosition.put(21, 42);
    ladderAndSnakePosition.put(16, 6);
    ladderAndSnakePosition.put(9, 51);
    ladderAndSnakePosition.put(4, 14);
    //  ruleMap.put(1, 38);
  }

  /**
   *
   */
  private enum PlayerCharEnum {
    /**
     * 0, ◐
     */
    FIRST(0, '◐'),
    /**
     * 1, ◑
     */
    SECOND(1, '◓'),
    /**
     * 2, ◑
     */
    THIRD(2, '◑'),
    /**
     * 3, ◒
     */
    FORTH(3, '◒');
    private final int index;
    private final char icon;

    PlayerCharEnum(int index, char icon) {
      this.index = index;
      this.icon = icon;
    }

    public static PlayerCharEnum getByIndex(int index) {
      for (PlayerCharEnum player : PlayerCharEnum.values()) {
        if (player.getIndex() == index) {
          return player;
        }
      }
      return null;
    }

    public int getIndex() {
      return this.index;
    }

    public char getChar() {
      return this.icon;
    }
  }

  private static class Setting {

    /**
     * Adjust the display pause time by  millis, To speed up the debugging set 0L No Pause.
     */
    private static final long GAME_SPEED = 200L;
    private static final boolean AUTO_RUN = true;
    private static final boolean DEVELOPMENT_MODE = true;
    private static final int BROAD_SIZE = 100;
    private static final int PLAYER_START_POSITION = 70;
  }

  private static class Player {

    String name;
    /**
     * the position in the board, 0-100, 101-105
     */
    int position = Setting.PLAYER_START_POSITION;
    /**
     * store the order and icon information
     */
    PlayerCharEnum orderOfStart = PlayerCharEnum.FIRST;
    int diceValue = 0;

    Player(String name) {
      this.name = name;
    }

    public Player(String name, int order) {
      this.name = name;
      this.orderOfStart = PlayerCharEnum.getByIndex(order);
    }

    public PlayerCharEnum getOrderOfStart() {
      return orderOfStart;
    }

    public void setOrderOfStart(int order) {
      this.orderOfStart = PlayerCharEnum.getByIndex(order);
    }
  }

}
