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
 * <p>
 *
 * Run successfully
 *
 * @author Mueataz Qasem Qasem, Dongguo
 * @version 1.1 {@code @date} 2022-12-19 00:08
 */
public class LadderAndSnake {

  private static final Queue<Player> playerQueueInPlaying = new LinkedList<>();
  private static final List<Player> playerListSortedByPosition = new ArrayList<>(4);
  private static final HashMap<Integer, Integer> ladderAndSnakePosition = new HashMap<>();
  private static final char[] boardRecord = new char[Setting.BROAD_SIZE + 1];
  private static final Random oneRandomToReuse = new Random();
  private static final Scanner oneScannerNeedToClose = new Scanner(System.in);

  public static void main(String[] args) {
    initLadderAndSnakePosition();
    refreshBoardRecord();
    displayBoard();

    List<Player> list = welcomePlayers();
//    List<Player> list = mockWelcomePlayers();
    initPlayers(list);
    play();
    oneScannerNeedToClose.close();
    //TODO: more information such as "After 11 steps, ... Win"
    System.out.println("\n\nBye");
    System.exit(0);
  }

  private static void displayBoard() {
    List<String> list = boardLiveInfo();
    for (String s : list) {
      System.out.println(s);
    }
    pauseGame("Welcome to Ladder And Snake Game.");
  }

  private static List<Player> mockWelcomePlayers() {
    return new LinkedList<>(
       List.of(new Player("Player 01", 2),
          new Player("Player 02", 1),
          new Player("Player 03", 0),
          new Player("Player 04", 3)));
  }

  /**
   * Add players and decide their order
   *
   * @return a list of Players who will take part in the game
   */
  private static List<Player> welcomePlayers() {
    // this is your code
    List<Player> playerList = new ArrayList<>(4);
    Scanner input = new Scanner(System.in);
    System.out.println("Enter A Number of players");
    int numPlayers = input.nextInt();

    // decide number of players (must be between 2 and 4)
    while (numPlayers < 2 || numPlayers > 4) {
      System.out.println("Number of players must be between 2 and 4 ");
      numPlayers = input.nextInt();
    }

    // create an array of players that is size numPlayers
    // and an array of integers that is size numPlayers to save the dice roll values
    int[] diceRolls = new int[numPlayers];
    for (int i = 0; i < numPlayers; i++) {
      System.out.println("Enter the name of player " + (i + 1) + ":");
      String playerName = input.next();
      Player player = new Player(playerName);
      System.out.println(playerName + ", press enter to roll the dice:");
      input.nextLine();  // consume newline character
      //      diceRolls[i] = Player.rollDice();  // roll the dice
      // whatever
      diceRolls[i] = generateRandomBetween1And6(oneRandomToReuse);  // roll the dice
      System.out.println(playerName + " rolled a " + diceRolls[i]);
      playerList.add(player);
    }

    // determine the order of playing turns
    while (true) {
      // find the player with the highest dice roll value
      int maxDiceRoll = 0;
      int maxDiceRollIndex = 0;
      for (int i = 0; i < numPlayers; i++) {
        if (diceRolls[i] > maxDiceRoll) {
          // explain
          maxDiceRoll = diceRolls[i];
          maxDiceRollIndex = i;
        }
      }

      // check if there are any ties in the dice roll values
      boolean tieExists = false;
      for (int i = 0; i < numPlayers; i++) {
        if (i != maxDiceRollIndex && diceRolls[i] == maxDiceRoll) {
          tieExists = true;
          break;
        }
      }

      // if there are no ties, set the order of start for the player with the highest dice roll
      // and set the order of start for the remaining players based on the dice roll values
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
      break; // break is not jump to the while loop
    }

    return playerList;
  }

  /**
   * Order the players by the orderOfStart property <p />Add the sorted players into the playerQueueInPlaying and
   * playerListSortedByPosition
   */
  private static void initPlayers(List<Player> list) {
    list.sort((a, b) -> a.orderOfStart.getIndex() - b.orderOfStart.getIndex());
    playerQueueInPlaying.addAll(list);
    playerListSortedByPosition.addAll(list);
    displayPlayers(null);
  }

  private static void play() {
    if (playerQueueInPlaying.isEmpty()) {
      System.out.println("No player is ready.");
      return;
    }

    Player currentPlayer;
    while (true) {
      currentPlayer = playerQueueInPlaying.poll();
      assert currentPlayer != null;

      currentPlayer.position = go(currentPlayer);
      playerQueueInPlaying.add(currentPlayer);

      displayPlayers(playerQueueInPlaying.peek());
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
   * @param player a player who is moving
   * @return the position where the player will go
   */
  private static int go(Player player) {
    int position = player.position;
    System.out.printf("%n%s(position=%d) :  go....\t", player.name, player.position);
    pauseGame("(Press Enter to flip dice)");
    int diceValue = generateRandomBetween1And6(oneRandomToReuse);
    System.out.println("got a dice value of " + diceValue);
    System.out.printf("move to %d = %d + %d%n", position + diceValue, position, diceValue);
    position += diceValue;
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
      displayPlayers(player);
      pauseGame("Please press Enter to continue");
    }

    return position;
  }

  private static void pauseGame(String message) {
    if (Setting.AUTO_RUN == false) {
      System.out.printf(String.format("%s", message));
      oneScannerNeedToClose.nextLine();
    }
  }

  private static int flipDice() {
    return generateRandomBetween1And6(oneRandomToReuse);
  }

  private static int generateRandomBetween1And6(Random random) {
    int minDice = 1;
    int maxDice = 6;
    int realValue = minDice + random.nextInt(maxDice);
    displayDice(realValue, maxDice);
    return realValue;
  }

  /**
   * Just display No other effect
   *
   * @param realValue a number that will be printed in the end
   * @param width     a number that decide the width of the display
   */
  private static void displayDice(int realValue, int width) {
    long pauseTime = Setting.GAME_SPEED;

    for (int i = 0; i < width * realValue; i++) {
      pauseDisplay(pauseTime / 10);
      System.out.printf("%d..", 1 + oneRandomToReuse.nextInt(realValue));
    }
    pauseDisplay(pauseTime * 3);
    System.out.printf("%s.", realValue);
    pauseDisplay(pauseTime * 2);
    System.out.printf("%s ", realValue);
    pauseDisplay(pauseTime);
    System.out.printf("%s%n", realValue);
    //    pauseDisplay(pauseTime * 10);
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

  /**
   * Dongguo is working Display the player information.
   */
  private static void displayPlayers(Player currentPlayer) {
    List<String> list = boardLiveInfo();
    List<String> playerInfo = playersLiveInfo(currentPlayer);
    System.out.printf("%n%n%n");
    final int scoresInfoPosition = 3;
    final int scoresInfoHeight = playerInfo.size();
    for (int i = 0; i < list.size(); i++) {
      if (i >= scoresInfoPosition && i < scoresInfoPosition + scoresInfoHeight) {
        int j = i - scoresInfoPosition;
        System.out.println(list.get(i) + "\t\t" + playerInfo.get(j));
      } else {
        System.out.println(list.get(i));
      }
    }
  }

  private static char[] refreshBoardRecord() {
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

  private static List<String> boardLiveInfo() {
    List<String> list = new ArrayList<>(12);

    System.arraycopy(refreshBoardRecord(), 0, boardRecord, 0, Setting.BROAD_SIZE + 1);
    fillPlayerPosition(boardRecord);

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
          stringToAddInList.append(boardRecord[indexOfMap]).append(" ");
        }
        stringToAddInList.append("  ");
      } else {
        stringToAddInList.append("  ");
        for (int j = 1; j <= 10; j++) {
          int indexOfMap = (9 - i) * 10 - j + 11;
          stringToAddInList.append(boardRecord[indexOfMap]).append(" ");
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
  private static List<String> playersLiveInfo(Player currentPlayer) {
    List<String> list = new ArrayList<>(5);
    playerListSortedByPosition.sort((a, b) -> b.position - a.position);
    for (Player player : playerListSortedByPosition) {
      boolean isCurrentPlayer = false;
      if (currentPlayer != null) {
        isCurrentPlayer = player.name.equalsIgnoreCase(currentPlayer.name);
      }
      list.add(
         String.format("%4s %c %-16s now in square: %3d", isCurrentPlayer ? "->" : "  ", player.orderOfStart.getChar(),
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
  private static void initLadderAndSnakePosition() {
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
    private static final boolean AUTO_RUN = false;
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
