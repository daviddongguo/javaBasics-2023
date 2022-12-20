import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    Random oneRandomToReuse = new Random();
    Scanner oneKeyBoardInputNeedToClose = new Scanner(System.in);
    List<Player> playerList = mockPlayers();
    IEarnable randomDice = new RandomDice(oneRandomToReuse);
    IEarnable magicDic = new RandomDice(oneRandomToReuse);

    BaseDiceGame miniGame = new MiniDiceGame(new ArrayList<>(), oneRandomToReuse, oneKeyBoardInputNeedToClose,
       randomDice);
    playerList = miniGame.play();

    BaseDiceGame game = new LadderAndSnakeGame(playerList, oneRandomToReuse, oneKeyBoardInputNeedToClose,
       randomDice);
    //        BaseDiceGame game = new LadderAndSnakeGame(playerList, oneRandomToReuse, oneKeyBoardInputNeedToClose,
    //           new MagicDice(oneKeyBoardInputNeedToClose));

    game.initialize();
    game.play();
    game.close();
  }

  private static List<Player> mockPlayers() {
    return new ArrayList<>(
       List.of(new Player("Player 01"),
          new Player("Player 02"),
          new Player("Player 03"),
          new Player("Player 04")));
  }
}