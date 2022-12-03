package xyz.dongguo.lesson;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Day07Test {

  private Day07 solution;

  @BeforeEach
  protected void init() {
    solution = new Day07(System.in, System.out);
  }

  @Test
  @DisplayName("1 + 1 = 2")
  void addsTwoNumbers() {
    assertEquals(2, solution.add(1, 1), "1 + 1 should equal 2");
  }

  @ParameterizedTest(name = "{0} + {1} = {2}")
  @CsvSource({
     "0,    1,   1",
     "1,    2,   3",
     "49,  51, 100",
     "1,  100, 101"
  })
  void add(int first, int second, int expectedResult) {
    assertEquals(expectedResult, solution.add(first, second),
       () -> first + " + " + second + " should equal " + expectedResult);
  }
}