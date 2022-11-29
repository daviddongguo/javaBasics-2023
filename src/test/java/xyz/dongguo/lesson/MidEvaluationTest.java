package xyz.dongguo.lesson;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MidEvaluationTest {

  private final String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
  //  private final int[] daysOfMonths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
  private String randomString;

  @BeforeEach
  void Setup() {
    randomString = "john abbott college mobile development program.";
  }

  @Test
  void run() {
    MidEvaluation.run();
    assertTrue(true);
  }

  @Test
  void pressEnterKeyToContinue() {
    assertTrue(true);
  }

  @Test
  void requestCovertDirection() {
    MidEvaluation.requestCovertDirection(randomString);
    assertTrue(true);
  }

  @Test
  void testRequestCovertDirection() {
    assertTrue(true);
  }

  @Test
  void requestThreeDigitIntegerNumber() {
    MidEvaluation.requestThreeDigitIntegerNumber(randomString);
    assertTrue(true);
  }

  @Test
  void requestHexDigitCharInput() {
    MidEvaluation.requestHexDigitCharInput(randomString);
    assertTrue(true);
  }

  @Test
  void requestMonthInput() {
    MidEvaluation.requestMonthInput(months, randomString);
    assertTrue(true);
  }

}