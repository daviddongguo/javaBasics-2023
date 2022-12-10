package xyz.dongguo.lesson.basic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import xyz.dongguo.lesson.basic.Day04;

class Day04Test {

  private Day04 solution;

  @BeforeEach
  void init() {
    solution = new Day04();
  }

  @Test
  void getClassName() {
    String result = solution.getClassName();
    String[] fullName = Day04.class.getName().split("\\.");
    String expected = fullName[fullName.length - 1];
    assertEquals(expected, result);

  }
}
