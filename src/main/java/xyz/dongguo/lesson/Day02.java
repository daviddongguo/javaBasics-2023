package xyz.dongguo.lesson;

public class Day02 {

  public static double calculateAreaOfCircle(
      double radius) {
    return Math.PI * radius * radius;
  }

  public static String timeSecondsToString(int time) {
    int minutes = time / 60;
    int seconds = time % 60;

    return String.format("%d : %d", minutes, seconds);
  }

  public static double convertCelToFah(double cel) {
    return cel * 9 / 5 + 32;
  }

  private Day02() {
  }
}
