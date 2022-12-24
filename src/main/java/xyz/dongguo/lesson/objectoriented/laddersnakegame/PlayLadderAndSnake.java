package xyz.dongguo.lesson.objectoriented.laddersnakegame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * @author
 */
public class PlayLadderAndSnake extends BaseDiceGame {

  public PlayLadderAndSnake(List<? extends Player> playerList, Random random, Scanner scanner,
     IEarnable dice) {
    super(playerList, random, scanner, dice);
  }

  public void main() {
    welcomePlayers();
  }

  @Override
  public void initialize() {
    // noting need to do
  }

  @Override
  public List<Player> play() {
    // upcasting, convert a subclass to superclass
    return new ArrayList<>(welcomePlayers());
  }

  @Override
  public void close() {
    // noting need to do
  }

  /**
   * Add players and decide their order
   *
   * @return a list of Players who will take part in the game rename partTwo to something else
   */
  private List<LadderAndSnakeGamePlayer> welcomePlayers() {
    System.out.println("\n\t\tWelcome to Ladder And  Snake Game");
    System.out.println("\t\tAl-Quaiti, Mueataz Qasem Qasem");
    System.out.println("\t\tWU, Dongguo\n");
    List<LadderAndSnakeGamePlayer> playerList = partTwoDecideNumberOfPlayersAndTheirName();

    if (playerList.isEmpty()) {
      return new ArrayList<>();
    }
    List<LadderAndSnakeGamePlayer> finalList = new ArrayList<>();
    System.out.printf("%n\t\tGame is played by %d players%n", playerList.size());
    System.out.println("Now deciding which player will start playing;");
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

    HashMap<Integer, List<LadderAndSnakeGamePlayer>> map = new HashMap<>(20);
    for (LadderAndSnakeGamePlayer player : listToDecide) {
      var list = map.get(player.diceValue);
      if (list == null) {
        list = new ArrayList<>(List.of(player));
        map.put(player.diceValue, list);
      } else {
        list.add(player);
      }
    }

    for (int i = Setting.MAX_VALUE_OF_DICE; i >= 0; i--) {
      List<LadderAndSnakeGamePlayer> list = map.get(i);
      if (list == null) {
        continue;
      }
      if (list.size() == 1) {
        finalList.add(list.get(0));
      }
      if (list.size() >= 2) {
        if(i != 0 ){
          System.out.print("\nA tie was achieved between ");
        }
        for (LadderAndSnakeGamePlayer currentPlayer : list) {
          System.out.print(currentPlayer.name + " ");
        }
        System.out.print("Attempting to break the tie\n");

        for (LadderAndSnakeGamePlayer currentPlayer : list) {
          System.out.printf("%n%s is throwing a dice.%n", currentPlayer.name);
          currentPlayer.diceValue = dice.earnScore();
          System.out.printf("%s get a dice value of %d %n", currentPlayer.name, currentPlayer.diceValue);
        }
        decideOrderOfStart(finalList, list);
      }
    }

  }

  private void qasemWelcomePlayers() { // determine the order of playing turns
    List<LadderAndSnakeGamePlayer> playerList = partTwoDecideNumberOfPlayersAndTheirName();
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
          //          diceRolls[tiedPlayerIndex] = laddersnakegame.Player.rollDice();  // re-roll the dice
          diceRolls[tiedPlayerIndex] = flipDice();  // re-roll the dice
          System.out.println(playerList.get(tiedPlayerIndex).name + " rolled a " + diceRolls[tiedPlayerIndex]);
        }

      }
      break;
    }
  }

  private List<LadderAndSnakeGamePlayer> partTwoDecideNumberOfPlayersAndTheirName() {

    int numberOfPlayers = decideNumberOfPlayers();
    if (numberOfPlayers <= 0) {
      return new ArrayList<>();
    }

    List<LadderAndSnakeGamePlayer> playerList = new ArrayList<>();
    namePlayers(playerList, numberOfPlayers);

    return playerList;
  }

  private int decideNumberOfPlayers() {
    int numPlayers = 0;
    System.out.println("Enter A Number of players(2-4): ");
    int totalAttempts = 4;
    for (int count = 1; count <= totalAttempts; count++) {
      numPlayers = scanner.nextInt();
      boolean isCorrectNumber = numPlayers >= 2 && numPlayers <= 4;
      if(isCorrectNumber){
        return numPlayers;
      }
      boolean isLastTime = count == totalAttempts;
      boolean isLastSecondTime = count == totalAttempts - 1;
      if (isLastTime) {
        System.out.println("Bad Attempt " + count +
           " ! You have exhausted all your chances. The program will terminate!");
        return -1;
      }
      if (isLastSecondTime) {
        System.out.println("Bad Attempt " + count +
           " - Invalid number of players. Please enter a number between 2 and 4 inclusively. This is your last attempt: ");
        continue;
      }
      System.out.println("Bad Attempt " + count +
         " - Invalid number of players. Please enter a number between 2 and 4 inclusively:");
    }
    //    numPlayers = scanner.nextInt();
    //    while (numPlayers < 2 || numPlayers > 4) {
    //      System.out.println("Number of players must be between 2 and 4 ");
    //      numPlayers = scanner.nextInt();
    //    }
    return numPlayers;
  }

  private void namePlayers(List<LadderAndSnakeGamePlayer> playerList, int numberOfPlayers) {
    for (int i = 0; i < numberOfPlayers; i++) {
      String playerName = "";
      boolean isDuplicateName = false;
      // F || T = T
      // T ||   = T
      // F || F = F
      while (isDuplicateName || "".equals(playerName)) {
        System.out.println("Enter the name of player " + (i + 1) + ":");
        playerName = scanner.next();
        for (LadderAndSnakeGamePlayer currentPlayer : playerList) {
          if (currentPlayer.name.equalsIgnoreCase(playerName)) {
            System.out.println("name can not be duplicated.");
            isDuplicateName = true;
            break;
          }
          isDuplicateName = false;
        }
      }
      LadderAndSnakeGamePlayer player = new LadderAndSnakeGamePlayer(playerName);
      playerList.add(player);
      System.out.printf("Added %s%n", player.name);
      scanner.nextLine();  // consume newline character
    }
  }

  /**
   * Include display effect
   *
   * @return 1, 2, ... 6
   */
  private int flipDice() {
    return dice.earnScore();
  }
}
