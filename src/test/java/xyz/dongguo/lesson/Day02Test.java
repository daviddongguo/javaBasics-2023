package xyz.dongguo.lesson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import xyz.dongguo.lesson.Day02;
import xyz.dongguo.utility.ConsoleOutput;

class Day02Test {

  @Test
  void alwaysPass() {
    assertTrue(true);
    assertFalse(false);
    assertNull(null);
  }


  @Test
  void calculateArea() {
    double input = 1.0;
    double expected = Math.PI * input * input;

    double result = Day02.calculateAreaOfCircle(input);

    assertEquals(expected, result);

    ConsoleOutput.println(String.format("area of a "
            + "circle(radius: %f): %f", input, result));
  }

  @Test
  void convertCelToFah() {
    double input = 31;
    double result = Day02.convertCelToFah(input);
    double expected = input * 9 / 5 + 32;

    assertEquals(expected, result);
    ConsoleOutput.println(String.format("%5.2f cel = %5.2f "
        + "fal", input, result));
  }

  @Test
  void timeSecondsToString() {
    int input = 70;
    String result = Day02.timeSecondsToString(input);
    String expected = "1 : 10";

    assertEquals(expected, result);
    ConsoleOutput.println(String.format("%d seconds is  "
        + "%s", input, result));
  }

  @Test
  void getMontrealTime() {
    ConsoleOutput.println(Day02.getMontrealTime());
    assertTrue(true);
  }

  @Test
  void calculatePaymentMonthly() {
    double initialLoan = 100.0;
    double interestRateMonthly = 0.01;
    int numberOfYear = 1;

    double paymentMonthly = Day02.calculatePaymentMonthly(initialLoan, interestRateMonthly, numberOfYear);

    double calculatedInitialLoan = getCalculatedInitialLoan(
        interestRateMonthly, numberOfYear,
        paymentMonthly);

    ConsoleOutput.println(String.format("monthlyPayment: "
        + "%4.2f", paymentMonthly));
    ConsoleOutput.println(String.format("total payment "
        + "in  %d months: %4.2f",
        numberOfYear * 12,
        paymentMonthly * numberOfYear * 12));

    assertEquals(calculatedInitialLoan, initialLoan);
  }

  private double getCalculatedInitialLoan(
      double interestRateMonthly, int numberOfYear,
      double paymentMonthly) {
    return paymentMonthly
        * (1 - (1 / Math.pow((1 + interestRateMonthly),
        (numberOfYear * 12)))) / interestRateMonthly;
  }
}