package xyz.dongguo.lesson;

public enum MonthsEnum {
  JANUARY(1) {
    @Override
    public int getDays() {
      return 31;
    }
  },
  FEBRUARY(2) {
    @Override
    public int getDays() {
      return this.isLeap ? 29 : 28;
    }
  },
  MARCH(3) {
    @Override
    public int getDays() {
      return 31;
    }
  },
  APRIL(4) {
    @Override
    public int getDays() {
      return 30;
    }
  },
  MAY(5) {
    @Override
    public int getDays() {
      return 31;
    }
  },
  JUNE(6) {
    @Override
    public int getDays() {
      return 30;
    }
  },
  JULY(7) {
    @Override
    public int getDays() {
      return 31;
    }
  },
  AUGUST(8) {
    @Override
    public int getDays() {
      return 31;
    }
  },
  SEPTEMBER(9) {
    @Override
    public int getDays() {
      return 30;
    }
  },
  OCTOBER(10) {
    @Override
    public int getDays() {
      return 31;
    }
  },
  NOVEMBER(11) {
    @Override
    public int getDays() {
      return 30;
    }
  },
  DECEMBER(12) {
    @Override
    public int getDays() {
      return 31;
    }
  };

  private final int order;
  protected boolean isLeap = false;

  MonthsEnum(int order) {
    this.order = order;
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

  protected void setLeap(boolean isLeap) {
    this.isLeap = isLeap;
  }

  public abstract int getDays();
}
