package dong.java;

public class Logger {
  //TODO: Standard outputs should not be used directly to log anything
  public static void info(String message) {
    System.out.println(message);
  }

  private Logger() {
  }
}