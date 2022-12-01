package xyz.dongguo;

import java.io.InputStream;
import java.io.PrintStream;
import xyz.dongguo.lesson.Day06;

public class Main {

  public static void main(String[] args) {
    System.out.printf("%n\t\t\t%s%n", "Java Application Started.");
    InputStream in = System.in;
    PrintStream out = System.out;

    (new Day06(in, out)).run();
  }

}