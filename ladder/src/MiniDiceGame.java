import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MiniDiceGame extends BaseDiceGame{

  @Override
  public void initialize() {

  }

  public MiniDiceGame(List<? extends Player> playerList, Random random, Scanner scanner,
     IMovable dice) {
    super(playerList, random, scanner, dice);
  }

  @Override
  public List<Player> play() {
    return null;
  }

  @Override
  public void close() {

  }
}
