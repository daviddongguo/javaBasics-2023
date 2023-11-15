package xyz.dongguo.interview;

public class CountDays {

  public static void main(String[] args) {

    System.out.println("hello");

    MyDate dateFrom = new MyDate("2020-01-06");
    MyDate dateEnd = new MyDate("2020-01-06");

    int days = 0;
    int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    for (int i = dateFrom.year; i <= dateEnd.year; i++) {
      boolean isLeap = isLeap(i);
      if (i < dateEnd.year) {
        days += 365;
        days += isLeap ? 1 : 0;
        continue;
      }
      for (int j = dateFrom.month; j <= dateEnd.month; j++) {
        if (j < dateEnd.month ) {
          days += daysInMonth[j - 1];
          if(j == 2 && isLeap){
            days++;
          }
          continue;
        }
        for (int k = dateFrom.day; k < dateEnd.day; k++) {
          days++;
        }
      }
    }

    System.out.println(days);
  }

  public static boolean isLeap(int year) {
    return (year % 400 == 0) || (year % 4 == 0 && year % 100 != 0);
  }

  public static int parseInt(String str) {
    try {
      return Integer.parseInt(str);
    } catch (Exception e) {
      return 0;
    }
  }

  private static class MyDate {

    int year;
    int month;
    int day;

    public MyDate(String str) {
      String[] arr = str.split("-");
      if (arr.length == 3) {
        this.year = parseInt(arr[0]);
        this.month = parseInt(arr[1]);
        this.day = parseInt(arr[2]);
      }
    }
  }

}

