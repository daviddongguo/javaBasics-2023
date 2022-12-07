package xyz.dongguo.lesson.basic;

import java.util.Objects;

/**
 * @author dongg
 */
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

  public static String calculateMontrealTime(double timeSeconds, int offSetByTimeZone) {

    int second = (int) timeSeconds % 60;
    int minute = (int) timeSeconds % 3600 / 60;
    int hour = (int) (timeSeconds % (24 * 3600) / 3600 + offSetByTimeZone);

    int year = (int) (timeSeconds / (365.25 * 24 * 3600) + 1970);
    int days = (int) (timeSeconds % (365.25 * 24 * 3600) / (24 * 3600));
    MonthsEnum month = getMonth(days, year);
    int dayOfMonth = getDayOfMonth(days, year);

    return String.format("Montreal time: %d-%s-%d  %d:%d:%d",
       year,
       month,
       dayOfMonth,
       hour,
       minute,
       second);
  }

  public static MonthsEnum getMonth(int days, int year) {
    boolean isLeap = MonthsEnum.isLeap(year);

    int indexOfMonth = 0;
    int amountOfDays = Objects.requireNonNull(MonthsEnum.getMonth(0)).getDays();
    final int lastIndexOfMonth = 11;
    while (indexOfMonth < lastIndexOfMonth) {
      if (days <= amountOfDays) {
        return MonthsEnum.getMonth(indexOfMonth);
      }
      indexOfMonth++;
      amountOfDays += Objects.requireNonNull(MonthsEnum.getMonth(indexOfMonth)).getDays(isLeap);
    }
    return MonthsEnum.DECEMBER;
  }

  public static int getDayOfMonth(int days, int year) {
    int dayOfMonth = days;
    boolean isLeap = MonthsEnum.isLeap(year);

    int indexOfMonth = 0;
    final int lastIndexOfMonth = 11;
    while (indexOfMonth < lastIndexOfMonth) {
      if (dayOfMonth <= Objects.requireNonNull(MonthsEnum.getMonth(indexOfMonth)).getDays(isLeap)) {
        return dayOfMonth;
      }
      indexOfMonth++;
      dayOfMonth -= Objects.requireNonNull(MonthsEnum.getMonth(indexOfMonth)).getDays(isLeap);
    }
    return dayOfMonth;
  }

  public static double calculatePaymentMonthly(double initialLoan, double interestRateMonthly,
     int numberOfYear) {
    return (initialLoan * interestRateMonthly) / (1 - 1 / Math.pow((1 + interestRateMonthly), (numberOfYear * 12)));
  }
}
