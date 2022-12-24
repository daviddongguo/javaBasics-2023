package xyz.dongguo.lesson.objectoriented.laddersnakegame;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public abstract class BaseDiceGame {

  /**
   * Means "a list of some type (which I don't actually know) which is a subtype of Player (or possibly Player itself)"
   */
  protected final List<? extends Player> playerList;
  protected InputStream in;
  protected IEarnable dice;
  protected Scanner scanner;

  protected BaseDiceGame(List<? extends Player> playerList, InputStream in, IEarnable dice) {
    this.playerList = playerList;
    this.in = in;
    this.dice = dice;
    scanner = new Scanner(in);
  }

  public abstract void initialize();

  public abstract List<Player> play();

  public void close() {
    scanner.close();
  }
}
