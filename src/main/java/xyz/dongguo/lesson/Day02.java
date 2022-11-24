package xyz.dongguo.lesson;

import static java.lang.System.currentTimeMillis;

public class Day02 {

  private Day02() {
  }

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

  public static String getMontrealTime() {
    double totalSeconds = currentTimeMillis() / 1000.0;

    int second = (int) totalSeconds % 60;
    int minute = (int) totalSeconds  % 3600 / 60;
    int hour = (int) (totalSeconds % (24 * 3600) / 3600 - 5);

    return String.format("Montreal time: %d:%d:%d", hour,
        minute,
        second);
  }

  public static double calculatePaymentMonthly(double initialLoan, double interestRateMonthly,
      int numberOfYear) {
    return (initialLoan * interestRateMonthly) / (1 - 1 / Math.pow((1 + interestRateMonthly), (numberOfYear * 12)));
      }
}
