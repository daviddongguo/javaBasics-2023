package xyz.dongguo.lesson.basic;

/**
 * Months {@link #JANUARY} {@link #FEBRUARY} {@link #MARCH} {@link #APRIL} {@link #MARCH} {@link #JUNE} {@link #JULY}
 * {@link #AUGUST} {@link #SEPTEMBER} {@link #OCTOBER} {@link #NOVEMBER} {@link #DECEMBER}
 *
 * @author dongguo
 */
public enum MonthsEnum {
  /**
   * January
   */
  JANUARY(0, 31),
  /**
   * February
   */
  FEBRUARY(1, 28),
  /**
   * March
   */
  MARCH(2, 31),
  /**
   * April
   */
  APRIL(3, 30),
  /**
   * May
   */
  MAY(4, 31),
  /**
   * June
   */
  JUNE(5, 30),
  /**
   * July
   */
  JULY(6, 31),
  /**
   * August
   */
  AUGUST(7, 31),
  /**
   * September
   */
  SEPTEMBER(8, 30),
  /**
   * October
   */
  OCTOBER(9, 31),
  /**
   * November
   */
  NOVEMBER(10, 30),
  /**
   * December
   */
  DECEMBER(11, 31);

  private final int index;
  private final int days;

  MonthsEnum(int index, int days) {
    this.index = index;
    this.days = days;
  }

  public static MonthsEnum getMonth(int order) {
    for (MonthsEnum month : MonthsEnum.values()) {
      if (month.getIndex() == order) {
        return month;
      }
    }
    return null;
  }

  public int getIndex() {
    return index;
  }

  public int getDays(boolean leapYear) {
    if (leapYear && this == FEBRUARY) {
      return 29;
    }
    return this.getDays();
  }

  public int getDays() {
    return this.days;
  }

  public static boolean isLeap(int year) {
    return (year % 400 == 0) || (year % 4 == 0 && year % 100 != 0);
  }
}
