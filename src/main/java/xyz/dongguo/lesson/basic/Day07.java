package xyz.dongguo.lesson.basic;

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
    long usedMB = 0L;
    out.printf("memory usage: %,dMB%n", usedMB);
    double[] a = new double[10000000];
    usedMB = memInfo(usedMB);
    out.printf("memory usage: %,dMB%n", usedMB);
    Double[] b = new Double[10000000];
    usedMB = memInfo(usedMB);
    out.printf("memory usage: %,dMB%n", usedMB);
    double qq = 3.1d;
    for (int i = 0; i < b.length; i++) {
      b[i] = qq * Math.random();
    }
    usedMB = memInfo(usedMB);
    out.printf("memory usage: %,dMB%n", usedMB);
    out.println("Press any key to continue.");
    scanner.nextLine();
  }

  private long memInfo(Long currentMemory) {
    Runtime rt = Runtime.getRuntime();
    long usedMB = (rt.totalMemory() - rt.freeMemory());
    return usedMB - currentMemory;
  }

  public int add(int a, int b) {
    return a + b;
  }
}
