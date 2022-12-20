import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MiniDiceGame extends BaseDiceGame {

  public MiniDiceGame(List<? extends Player> playerList, Random random, Scanner scanner,
     IMovable dice) {
    super(playerList, random, scanner, dice);
  }

  @Override
  public void initialize() {

  }

  @Override
  public List<Player> play() {
    List<Player> list = new ArrayList<>();
    for (LadderAndSnakeGamePlayer currentPlayer: welcomePlayers()){
      list.add(new Player(currentPlayer.name));
    }

    return list;
  }

  @Override
  public void close() {

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

}
