import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    //    printHeart();

    Random oneRandomToReuse = new Random();
    Scanner oneKeyBoardInputNeedToClose = new Scanner(System.in);
    IEarnable randomDice = new RandomDice(oneRandomToReuse);
    IEarnable magicDic = new MagicDice(oneKeyBoardInputNeedToClose);
    Board board = new Board();
    List<Player> playerList;

    if (Setting.AUTO_RUN) {
      playerList = mockPlayers();
    } else {
      BaseDiceGame miniGame = new PlayLadderAndSnake(new ArrayList<>(), oneRandomToReuse, oneKeyBoardInputNeedToClose,
         magicDic);
      playerList = miniGame.play();

    }

    BaseDiceGame game = new LadderAndSnake(playerList, oneRandomToReuse, oneKeyBoardInputNeedToClose,
       randomDice, board, playerList.size());

    game.initialize();
    game.play();
    printHeart();
    game.close();
  }

  private static List<Player> mockPlayers() {
    return new ArrayList<>(
       List.of(new Player("Player 01"),
          new Player("Player 02"),
          new Player("Player 03"),
          new Player("Player 04")));
  }

  private static void printHeart() {
    List<String> list = new ArrayList<>(List.of(
       "                                         ",
       "       *  *       *  *                   ",
       "    *       *   *      *                 ",
       "   *          *        *                ",
       "    *                 *                ",
       "      *             *                  ",
       "         *        *                    ",
       "            *   *                      ",
       "              *                        "
    ));
    System.out.printf("%n%n");
    list.forEach(System.out::println);
  }
}