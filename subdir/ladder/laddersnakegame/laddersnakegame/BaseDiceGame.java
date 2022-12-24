package laddersnakegame;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public abstract class BaseDiceGame {

  /**
   * Means "a list of some type (which I don't actually know)
   * which is a subtype of laddersnakegame.Player (or possibly laddersnakegame.Player itself)"
   */
  protected final List<? extends Player> playerList;
  protected Random random;
  protected Scanner scanner;
  protected IEarnable dice;

  public BaseDiceGame(List<? extends Player> playerList, Random random, Scanner scanner, IEarnable dice) {
    this.playerList = playerList;
    this.random = random;
    this.scanner = scanner;
    this.dice = dice;
  }

  public abstract void initialize();

  public abstract List<Player> play();

  public abstract void close();
}
