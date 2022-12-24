package xyz.dongguo.lesson.objectoriented.laddersnakegame;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author
 */
public class MiniDiceGame extends BaseDiceGame {

  public MiniDiceGame(List<? extends Player> playerList, InputStream in, IEarnable dice) {
    super(playerList, in, dice);
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

    HashMap<Integer, List<LadderAndSnakeGamePlayer>> map = initializeHashMap(listToDecide);
    for (int i = Setting.MAX_VALUE_OF_DICE; i >= 0; i--) {
      List<LadderAndSnakeGamePlayer> playerList = map.get(i);
      if (playerList == null) {
        continue;
      }
      if (playerList.size() == 1) {
        finalList.add(playerList.get(0));
      }
      if (playerList.size() >= 2) {
        boolean isFirstRace = i == 0;
        if (!isFirstRace) {
          System.out.print("\nA tie was achieved between ");
        }
        displayMutiplePlayerBreakTie(playerList);

        for (LadderAndSnakeGamePlayer currentPlayer : playerList) {
          System.out.printf("%n%s is throwing a dice.%n", currentPlayer.name);
          currentPlayer.diceValue = dice.earnScore();
          System.out.printf("%s get a dice value of %d %n", currentPlayer.name, currentPlayer.diceValue);
        }
        decideOrderOfStart(finalList, playerList);
      }
    }

  }

  private void displayMutiplePlayerBreakTie(List<LadderAndSnakeGamePlayer> playerList) {
    for (LadderAndSnakeGamePlayer currentPlayer : playerList) {
      System.out.print(currentPlayer.name + " ");
    }
    System.out.print("Attempting to break the tie\n");
  }

  private HashMap<Integer, List<LadderAndSnakeGamePlayer>> initializeHashMap(
     List<LadderAndSnakeGamePlayer> listToDecide) {
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
    return map;
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
      numPlayers = Utility.requestNumberBetweenMinAndMax(2, 4, scanner);
      boolean isCorrectNumber = numPlayers >= 2 && numPlayers <= 4;
      if (isCorrectNumber) {
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
