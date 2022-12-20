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
 * @author Mueataz Qasem Qasem, Dongguo
 * @version 1.3 {@code @date} 2022-12-19 23:50
 */
public class LadderAndSnakeGame extends BaseDiceGame {

  private final Queue<LadderAndSnakeGamePlayer> playerQueue = new LinkedList<>();
  private final List<LadderAndSnakeGamePlayer> playerListSortedByPosition = new ArrayList<>(4);
  private final HashMap<Integer, Integer> ladderAndSnakePosition = new HashMap<>();
  private final char[] boardDesign = new char[Setting.BROAD_SIZE + 1];

  public LadderAndSnakeGame(Random random, Scanner scanner, IMovable dice) {
    super(random, scanner, dice);
  }

  @Override
  public void initialize() {
    initializeLadderAndSnakePosition();
    initializeBoard();
    displayBoard();
  }

  @Override
  public void registerPlay() {
    List<LadderAndSnakeGamePlayer> list = welcomePlayers();
    //    List<LadderAndSnakeGamePlayer> list = mockWelcomePlayers();
    initializePlayer(list);
  }

  @Override
  public void play() {
    playLadderAndSnakeGame();
  }

  @Override
  public void close() {
    scanner.close();
    System.out.println("\n\nBye");
    System.exit(0);
  }

  /**
   * Display the game board that shows the position of players and ladder, snake.
   */
  private void displayBoard() {
    List<String> list = getLadderAndSnakeBoard();
    for (String s : list) {
      System.out.println(s);
    }
    pauseGame("Welcome to Ladder And Snake Game.");
  }

  private void pauseGame(String message) {
    if (Setting.AUTO_RUN == false) {
      System.out.printf(String.format("%s", message));
      scanner.nextLine();
    }
  }

  /**
   * the game board that include the position ladder and snake. and the position of players.
   *
   * @return a string list
   */
  private List<String> getLadderAndSnakeBoard() {
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

  private void fillPlayerPosition(char[] boardRecord) {
    for (LadderAndSnakeGamePlayer player : playerListSortedByPosition) {
      boardRecord[player.position] = player.orderOfStart.getChar();
    }
  }

  private char[] initializeBoard() {
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

  private void fillSnakeAndLadder(char charSnaker, char charLadder, char[] boardRecord) {
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
   * set all ladder and snake rules and moving backward rules when exceeding maximum
   */
  private void initializeLadderAndSnakePosition() {
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

  private List<LadderAndSnakeGamePlayer> mockWelcomePlayers() {
    return new LinkedList<>(
       List.of(new LadderAndSnakeGamePlayer("Player 01", 2),
          new LadderAndSnakeGamePlayer("Player 02", 1),
          new LadderAndSnakeGamePlayer("Player 03", 0),
          new LadderAndSnakeGamePlayer("Player 04", 3)));
  }

  /**
   * Order the players by the orderOfStart property <p />Add the sorted players into the playerQueueInPlaying and
   * playerListSortedByPosition
   */
  private void initializePlayer(List<LadderAndSnakeGamePlayer> list) {
    list.sort((a, b) -> a.orderOfStart.getIndex() - b.orderOfStart.getIndex());
    playerQueue.addAll(list);
    playerListSortedByPosition.addAll(list);
    displayPlayers(null);
  }

  /**
   * Dongguo is working Display the player information.
   */
  private void displayPlayers(LadderAndSnakeGamePlayer currentPlayer) {
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

  /**
   * Live Scores
   *
   * @return a list of string showing the players ordered by their position
   */
  private List<String> getPlayerScoreInfo(LadderAndSnakeGamePlayer currentPlayer) {
    List<String> list = new ArrayList<>(5);
    list.add("             SCORES");
    playerListSortedByPosition.sort((a, b) -> b.position - a.position);
    for (LadderAndSnakeGamePlayer player : playerListSortedByPosition) {
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

  /**
   * Add players and decide their order
   *
   * @return a list of Players who will take part in the game rename partTwo to something else
   */
  private List<LadderAndSnakeGamePlayer> welcomePlayers() {

    List<LadderAndSnakeGamePlayer> playerList = partTwo();

    List<LadderAndSnakeGamePlayer> finalList = new ArrayList<>();
    decideOrderOfStart(finalList, playerList);

    return finalList;
  }

  /**
   * raceOrderOfStart---> decideOrderOfStart
   */
  private void decideOrderOfStart(List<LadderAndSnakeGamePlayer> finalList,
     List<LadderAndSnakeGamePlayer> listToDecide) {
    if (listToDecide.size() == 1) {
      finalList.add(listToDecide.get(0));
    }

    HashMap<Integer, List<LadderAndSnakeGamePlayer>> map = new HashMap<>();
    for (LadderAndSnakeGamePlayer player : listToDecide) {
      var list = map.get(player.diceValue);
      if (list == null) {
        list = new ArrayList<>(List.of(player));
        map.put(player.diceValue, list);
      } else {
        list.add(player);
      }
    }

    for (int i = 6; i >= 0; i--) {
      List<LadderAndSnakeGamePlayer> list = map.get(i);
      if (list == null) {
        continue;
      }
      if (list.size() == 1) {
        finalList.add(list.get(0));
      }
      if (list.size() >= 2) {
        for (LadderAndSnakeGamePlayer currentPlayer : list) {
          System.out.printf("%s is throwing a dice.%n", currentPlayer.name);
          currentPlayer.diceValue = dice.getMovingSteps();
          System.out.printf("%s get a dice value of %d %n", currentPlayer.name, currentPlayer.diceValue);
        }
        decideOrderOfStart(finalList, list);
      }
    }

  }

  private void qasemWelcomePlayers() { // determine the order of playing turns
    List<LadderAndSnakeGamePlayer> playerList = partTwo();
    int numPlayers = 2;
    int[] diceRolls = new int[numPlayers];
    Scanner input = scanner;
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
  private int flipDice() {
    return dice.getMovingSteps();
  }

  private List<LadderAndSnakeGamePlayer> partTwo() {
    List<LadderAndSnakeGamePlayer> playerList = new ArrayList<>();
    //TODO: reuse one scanner in the whole project
    Scanner input = scanner;
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
      LadderAndSnakeGamePlayer player = new LadderAndSnakeGamePlayer(playerName);
      System.out.println(playerName + ", press enter to roll the dice:");
      input.nextLine();  // consume newline character

      //      diceRolls[i] = flipDice();  // roll the dice
      //      System.out.println(playerName + " rolled a " + diceRolls[i]);
      playerList.add(player);
    }
    return playerList;
  }

  private void playLadderAndSnakeGame() {
    if (playerQueue.isEmpty()) {
      System.out.println("No player is ready.");
      return;
    }

    LadderAndSnakeGamePlayer currentPlayer;
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
  private int go(LadderAndSnakeGamePlayer currentPlayer) {
    int position = currentPlayer.position;
    System.out.printf("%n%s(position=%d) :  go....\t", currentPlayer.name, currentPlayer.position);
    pauseGame("(Press Enter to flip dice)");

    int currentDice;
    currentDice = dice.getMovingSteps();

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
}
