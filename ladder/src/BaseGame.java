import java.util.Random;
import java.util.Scanner;

public abstract class BaseGame {

  protected Random random;
  protected Scanner scanner;
  protected IMovable dice;

  protected BaseGame(Random random, Scanner scanner, IMovable dice) {
    this.random = random;
    this.scanner = scanner;
    this.dice = dice;
  }

  public abstract void initialize();

  public abstract void registerPlay();

  public abstract void play();

  public abstract void close();
}
