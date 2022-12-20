import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    Random oneRandomToReuse = new Random();
    Scanner oneKeyBoardInputNeedToClose = new Scanner(System.in);
    IEarnable randomDice = new RandomDice(oneRandomToReuse);
    IEarnable magicDic = new MagicDice(oneKeyBoardInputNeedToClose);
    List<Player> playerList;

//    playerList = mockPlayers();
    BaseDiceGame miniGame = new MiniDiceGame(new ArrayList<>(), oneRandomToReuse, oneKeyBoardInputNeedToClose,
       magicDic);
    playerList = miniGame.play();

    BaseDiceGame game = new LadderAndSnakeGame(playerList, oneRandomToReuse, oneKeyBoardInputNeedToClose,
       randomDice);

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