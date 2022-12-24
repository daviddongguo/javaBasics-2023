package xyz.dongguo.lesson.objectoriented.laddersnakegame;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    Random oneRandomToReuse = new Random();
    IEarnable randomDice = new RandomDice(oneRandomToReuse);

    InputStream keyBoardInput = System.in;
    Scanner oneKeyBoardInputNeedToClose = new Scanner(keyBoardInput);

    IEarnable magicDic = new MagicDice(oneKeyBoardInputNeedToClose);

    List<Player> playerList;
    BaseDiceGame miniGame;
    BaseDiceGame game;
    IMovable board = new LadderAndSnakeBoard();
    if (Setting.AUTO_RUN) {
      InputStream mockInput = new ByteArrayInputStream("4 \nOlivia \nLily \nAva \nSophia".getBytes());
      miniGame = new MiniDiceGame(new ArrayList<>(), mockInput,
         randomDice);
      playerList = miniGame.play();
      game = new LadderAndSnakeBoardGame(playerList, keyBoardInput,
         randomDice, board);
    } else {
      miniGame = new MiniDiceGame(new ArrayList<>(), keyBoardInput,
         magicDic);
      playerList = miniGame.play();
      game = new LadderAndSnakeBoardGame(playerList, keyBoardInput,
         randomDice, board);
    }

    game.initialize();
    game.play();
    Utility.printHeart();
    oneKeyBoardInputNeedToClose.close();
    game.close();
    System.exit(0);
  }

}