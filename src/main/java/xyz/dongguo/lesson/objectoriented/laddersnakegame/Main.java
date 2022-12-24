package xyz.dongguo.lesson.objectoriented.laddersnakegame;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    //    printHeart();

    Random oneRandomToReuse = new Random();
    InputStream keyBoardInput = System.in;
    IEarnable randomDice = new RandomDice(oneRandomToReuse);
    Scanner oneKeyBoardInputNeedToClose = new Scanner(keyBoardInput);
    IEarnable magicDic = new MagicDice(oneKeyBoardInputNeedToClose);
    Board board = new Board();
    List<Player> playerList;

    if (Setting.AUTO_RUN) {
      InputStream mockInput = new ByteArrayInputStream("4 \nOlivia \nLily \nAva \nSophia".getBytes());
      BaseDiceGame miniGame = new MiniDiceGame(new ArrayList<>(), mockInput,
         randomDice);
      playerList = miniGame.play();

    } else {
      BaseDiceGame miniGame = new MiniDiceGame(new ArrayList<>(), keyBoardInput,
         magicDic);
      playerList = miniGame.play();

    }

    BaseDiceGame game = new LadderAndSnakeBoardGame(playerList, keyBoardInput,
       randomDice, board);

    game.initialize();
    game.play();
    printHeart();
    game.close();
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

  private static List<Player> mockPlayers() {
    return new ArrayList<>(
       List.of(new Player("Player 01"),
          new Player("Player 02"),
          new Player("Player 03"),
          new Player("Player 04")));
  }
}