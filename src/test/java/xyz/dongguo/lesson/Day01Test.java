package xyz.dongguo.lesson;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class Day01Test {

  @Test
  void printFullName() {
    assertAll("true",
       () -> {
         Day01.printFullName();
         assertTrue(true);
       });
  }

  @Test
  void operate() {
    int expected = 3;
    int result = Day01.operate(1, 2);
    assertEquals(result, expected);
  }
}