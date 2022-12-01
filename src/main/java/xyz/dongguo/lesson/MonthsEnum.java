package xyz.dongguo.lesson;

public enum MonthsEnum {
  JANUARY(1, 31),
  FEBRUARY(2, 28),
  MARCH(3, 31),
  APRIL(4, 30),
  MAY(5, 31),
  JUNE(6, 30),
  JULY(7, 31),
  AUGUST(8, 31),
  SEPTEMBER(9, 30),
  OCTOBER(10, 31),
  NOVEMBER(11, 30),
  DECEMBER(12, 31);

  private final int order;
  private final int days;

  MonthsEnum(int order, int days) {
    this.order = order;
    this.days = days;
  }

  public static MonthsEnum getMonth(int order) {
    for (MonthsEnum month : MonthsEnum.values()) {
      if (month.getOrder() == order) {
        return month;
      }
    }
    return null;
  }

  public int getOrder() {
    return order;
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

  public static boolean isLeap(int year){
    return (year % 400 == 0) || (year % 4 == 0 && year % 100 !=0);
  }
}
