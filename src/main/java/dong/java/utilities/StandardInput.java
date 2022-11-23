package dong.java.utilities;

import java.util.Scanner;

public class StandardInput {

  public static final Scanner scanner = new Scanner(System.in);

  public static String readLine() {
    return scanner.nextLine();
  }

  private StandardInput() {
  }
}