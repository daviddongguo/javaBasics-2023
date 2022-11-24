package com.dongguo.utility;

import java.util.Scanner;

public class ConsoleInput {

  public static final Scanner scanner = new Scanner(System.in);

  public static String readLine() {
    return scanner.nextLine();
  }

  private ConsoleInput() {
  }
}