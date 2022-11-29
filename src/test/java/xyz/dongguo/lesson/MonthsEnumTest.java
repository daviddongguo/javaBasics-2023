package xyz.dongguo.lesson;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MonthsEnumTest {

  final String[] MonthsArray = {"JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER",
     "OCTOBER", "NOVEMBER", "DECEMBER"};
  final MonthsEnum feb = MonthsEnum.FEBRUARY;

  @Test
  void getOrder() {
    assertEquals(2, feb.getOrder());
  }

  @Test
  void getDays() {
    assertEquals(28, feb.getDays());
  }

  @Test
  void getMonth() {
    MonthsEnum jan = MonthsEnum.getMonth(1);
    assert jan != null;
    assertEquals(MonthsArray[0], jan.name());
  }

  @Test
  void values() {
    int i = 0;
    for (MonthsEnum month : MonthsEnum.values()) {
      assertEquals(MonthsArray[i], month.toString());
      i++;
    }
  }

  @Test
  void valueOf() {
    MonthsEnum jan = Enum.valueOf(MonthsEnum.class, MonthsArray[0]);
    assertEquals(MonthsEnum.JANUARY, jan);
  }
}
