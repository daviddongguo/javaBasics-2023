package xyz.dongguo.lesson;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Day06 {
  private final Scanner scanner;
  private final PrintStream out;

  public Day06(InputStream in, PrintStream out){
    scanner = new Scanner(in);
    this.out = out;
  }

  public void run (){
    printInput();
  }

  private void printInput() {
    out.printf("%nPlease enter any string: ");
    out.println(scanner.nextLine());
  }

}
