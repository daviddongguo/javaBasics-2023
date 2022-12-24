package xyz.dongguo.lesson.objectoriented.laddersnakegame;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author Mueataz Qasem Qasem, Dongguo
 * @version 1.3 {@code @date} 2022-12-19 23:50
 */
public class LadderAndSnakeBoardGame extends BaseDiceGame {

  private final Board board;
  private final Queue<LadderAndSnakeGamePlayer> playerQueue = new LinkedList<>();
  private final List<LadderAndSnakeGamePlayer> playerListSortedByPosition = new ArrayList<>(4);
  private final HashMap<Integer, Integer> mapForLocateLadderAndSnake = new HashMap<>();
  private final char[] boardDesign = new char[Setting.BROAD_SIZE + 1];

  public LadderAndSnakeBoardGame(List<? extends Player> playerList, InputStream in, IEarnable dice, Board board
  ) {
    super(playerList, in, dice);
    this.board = board;
  }

  private int flipDice() {
    return dice.earnScore();
  }

  @Override
  public void initialize() {
    if (playerList.isEmpty()) {
      close();
    }
    initializeLadderAndSnakePosition();
    initializeBoard();
    displayBoard();
    initializePlayer(playerList);
  }

  @Override
  public List<Player> play() {
    return playLadderAndSnakeGame();
  }

  @Override
  public void close() {
    System.out.println("\n\nBye");
    System.exit(0);
  }

  private List<Player> playLadderAndSnakeGame() {
    if (playerQueue.isEmpty()) {
      System.out.println("No player is ready.");
      return new ArrayList<>();
    }

    LadderAndSnakeGamePlayer currentPlayer;
    while (true) {
      currentPlayer = playerQueue.poll();
      assert currentPlayer != null;

      currentPlayer.position = move(currentPlayer);
      playerQueue.add(currentPlayer);

      displayPlayers(playerQueue.peek());
      pauseGame("Please press Enter to continue");
      if (currentPlayer.position == Setting.BROAD_SIZE) {
        System.out.printf("%s wins.", currentPlayer.name);
        return new ArrayList<>();
      } else {
        System.out.println("Game is not over; flipping again");
      }

    }
  }

  /**
   * move based on a random number create by flip dice and on the rules of the game broad
   *
   * @param currentPlayer a player who is moving
   * @return the position where the player will go
   */
  private int move(LadderAndSnakeGamePlayer currentPlayer) {
    int position = currentPlayer.position;
    System.out.printf("%n%s(position=%d) :  go....\t", currentPlayer.name, currentPlayer.position);
    pauseGame("(Press Enter to flip dice)");

    int currentDice = flipDice();

    System.out.println("got a dice value of " + currentDice);
    System.out.printf("move to %d = %d + %d%n", position + currentDice, position, currentDice);
    position += currentDice;
    pauseGame("Please press Enter to continue");
    return upOrDownByUsingArray(position, currentPlayer);
  }

  private int upOrDownByUsingArray(int originalPosition, LadderAndSnakeGamePlayer currentPlayer) {
    int newPosition = board.ladderAndSnakePositionArray[originalPosition];
    if (newPosition == originalPosition) {
      return newPosition;
    }
    while (newPosition != originalPosition) {
      System.out.println("then...");
      System.out.println("continue move automatically");

      if (newPosition > originalPosition) {
        System.out.println("LADDER  " + "=|".repeat((newPosition - originalPosition) * 3));
      } else {
        System.out.println("SNAKE    " + "~".repeat((originalPosition - newPosition) * 3));
      }
      System.out.println("move to " + newPosition);
      displayPlayers(currentPlayer);
      pauseGame("Please press Enter to continue");
      originalPosition = newPosition;
      // continue to check if up or down
      newPosition = board.ladderAndSnakePositionArray[newPosition];
    }

    return newPosition;
  }

  private int upOrDownByUsingMap(int position, LadderAndSnakeGamePlayer currentPlayer) {
    while (mapForLocateLadderAndSnake.containsKey(position)) {
      System.out.println("then...");
      System.out.println("continue move automatically");

      int newPosition = mapForLocateLadderAndSnake.get(position);
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
    return 0;
  }

  private void pauseGame(String message) {
    if (!Setting.AUTO_RUN) {
      System.out.printf(String.format("%s", message));
      scanner.nextLine();
    }
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
   * the game board that include the position ladder and snake. and the position of players.
   *
   * @return a string list
   */
  private List<String> getLadderAndSnakeBoard() {
    List<String> list = new ArrayList<>(12);

    System.arraycopy(initializeBoard(), 0, boardDesign, 0, Setting.BROAD_SIZE + 1);
    fillPlayerPosition(boardDesign);

    // Table Header
    char[] romanNumbers = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
    StringBuilder stringToAddInList = new StringBuilder(" ");
    for (int i = romanNumbers.length - 1; i >= 0; i--) {
      stringToAddInList.append(String.format(" %s", romanNumbers[i]));
    }
    list.add(stringToAddInList.toString());

    // Table Body
    for (int i = 0; i <= 9; i++) {
      stringToAddInList = new StringBuilder();
      if (i % 2 == 1) {
        stringToAddInList.append(9 - i).append(" ");
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
        stringToAddInList.append(9 - i).append(" ");
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
    char charSnake = 'S';
    char charLadder = 'T';
    char charSquare = '*';
    char charFinal = 'F';
    char charStart = '1';

    Arrays.fill(chars, charSquare);
    chars[1] = charStart;
    chars[100] = charFinal;
    fillSnakeAndLadder(charSnake, charLadder, chars);
    return chars;
  }

  private void fillSnakeAndLadder(char charSnaker, char charLadder, char[] boardRecord) {
    for (Map.Entry<Integer, Integer> entry : mapForLocateLadderAndSnake.entrySet()) {
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
   * Display the game board that shows the position of players and ladder, snake.
   */
  private void displayBoard() {
    List<String> list = getLadderAndSnakeBoard();
    for (String s : list) {
      System.out.println(s);
    }
    pauseGame("Welcome to Ladder And Snake Game.");
  }

  /**
   * set all ladder and snake rules and moving backward rules when exceeding maximum
   */
  private void initializeLadderAndSnakePosition() {
    mapForLocateLadderAndSnake.put(105, 95);
    mapForLocateLadderAndSnake.put(104, 96);
    mapForLocateLadderAndSnake.put(103, 97);
    mapForLocateLadderAndSnake.put(102, 98);
    mapForLocateLadderAndSnake.put(101, 99);
    mapForLocateLadderAndSnake.put(98, 78);
    mapForLocateLadderAndSnake.put(97, 76);
    mapForLocateLadderAndSnake.put(95, 24);
    mapForLocateLadderAndSnake.put(93, 68);
    mapForLocateLadderAndSnake.put(80, 100);
    mapForLocateLadderAndSnake.put(79, 19);
    mapForLocateLadderAndSnake.put(71, 91);
    mapForLocateLadderAndSnake.put(64, 60);
    mapForLocateLadderAndSnake.put(51, 67);
    mapForLocateLadderAndSnake.put(48, 30);
    mapForLocateLadderAndSnake.put(36, 44);
    mapForLocateLadderAndSnake.put(28, 84);
    mapForLocateLadderAndSnake.put(21, 42);
    mapForLocateLadderAndSnake.put(16, 6);
    mapForLocateLadderAndSnake.put(9, 51);
    mapForLocateLadderAndSnake.put(4, 14);
    //  ruleMap.put(1, 38);
  }

  /**
   * Order the players by the orderOfStart property <p />Add the sorted players into the playerQueueInPlaying and
   * playerListSortedByPosition
   */
  private void initializePlayer(List<? extends Player> list) {
    int order = 0;
    for (Player currentPlayer : list) {
      LadderAndSnakeGamePlayer player = new LadderAndSnakeGamePlayer(currentPlayer.name, order++);
      playerListSortedByPosition.add(player);
      playerQueue.add(player);
    }
    displayPlayers(null);
  }

}
