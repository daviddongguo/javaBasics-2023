package xyz.dongguo.lesson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import xyz.dongguo.lesson.Day03.Operator;

class Day03Test {

  @Test
  void printSubtractions() {
    Day03.printSubtractions();
    assertTrue(true);
  }

  @Test
  void printRandomHiFive() {
    Day03.printRandomHiFive();
    assertTrue(true);
  }

  @Test
  void calculateTwoNumbers() {
    Day03.calculateTwoNumbers();
    assertTrue(true);
  }

  @Test
  void calculate() {
    int result = Day03.calculate(1, 2, Operator.ADD);
    assertEquals(3, result);
  }
}