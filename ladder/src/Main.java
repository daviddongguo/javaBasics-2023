import java.util.Random;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    Random oneRandomToReuse = new Random();
    Scanner oneKeyBoardInputNeedToClose = new Scanner(System.in);


//    BaseGame game = new LadderAndSnakeGame(oneRandomToReuse, oneKeyBoardInputNeedToClose, new RandomDice(oneRandomToReuse));
        BaseGame game = new LadderAndSnakeGame(oneRandomToReuse, oneKeyBoardInputNeedToClose,
           new MagicDice(oneKeyBoardInputNeedToClose));

    game.initialize();
    game.registerPlay();
    game.play();
    game.close();
  }
}