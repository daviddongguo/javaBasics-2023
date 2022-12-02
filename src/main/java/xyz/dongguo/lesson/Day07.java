package xyz.dongguo.lesson;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Day07 {

  private final Scanner scanner;
  private final PrintStream out;

  public Day07(InputStream in, PrintStream out) {
    scanner = new Scanner(in);
    this.out = out;
  }

  public void run() {
    out.println("This is Day06");
    out.println("Press any key to continue.");
    scanner.nextLine();
  }

  public int add(int a, int b) {
    return a + b;
  }
}
