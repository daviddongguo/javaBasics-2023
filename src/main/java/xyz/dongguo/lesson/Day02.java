package xyz.dongguo.lesson;

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
    int[] daysOfMonths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    if (isLeapYear(year)) {
      daysOfMonths[1] = 29;
    }

    int indexOfMonth = 0;
    int amountOfDays = daysOfMonths[0];
    while (indexOfMonth < 11) {
      if (days <= amountOfDays) {
        return MonthsEnum.getMonth(indexOfMonth + 1);
      }
      indexOfMonth++;
      amountOfDays += daysOfMonths[indexOfMonth];
    }
    return MonthsEnum.DECEMBER;
  }

  public static int getDayOfMonth(int days, int year) {
    int dayOfMonth = days;
    int[] daysOfMonths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    if (isLeapYear(year)) {
      daysOfMonths[1] = 29;
    }

    int indexOfMonth = 0;
    while (indexOfMonth < 11) {
      if (dayOfMonth <= daysOfMonths[indexOfMonth]) {
        return dayOfMonth;
      }
      indexOfMonth++;
      dayOfMonth -= daysOfMonths[indexOfMonth];
    }
    return dayOfMonth;
  }

  public static boolean isLeapYear(int year) {
    return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
  }

  public static double calculatePaymentMonthly(double initialLoan, double interestRateMonthly,
     int numberOfYear) {
    return (initialLoan * interestRateMonthly) / (1 - 1 / Math.pow((1 + interestRateMonthly), (numberOfYear * 12)));
  }
}
