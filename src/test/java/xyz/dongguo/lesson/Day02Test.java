package xyz.dongguo.lesson;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import xyz.dongguo.utility.ConsoleOutput;

class Day02Test {

  @Test
  void calculateArea() {
    double input = 1.0;
    double expected = Math.PI * input * input;

    double result = Day02.calculateAreaOfCircle(input);

    Assertions.assertEquals(expected, result);

    ConsoleOutput.println(String.format("area of a "
            + "circle(radius: %f): %f", input, result));
  }

  @Test
  void convertCelToFah() {
    double input = 31;
    double result = Day02.convertCelToFah(input);
    double expected = input * 9 / 5 + 32;

    Assertions.assertEquals(expected, result);
    ConsoleOutput.println(String.format("%5.2f cel = %5.2f "
        + "fal", input, result));
  }

  @Test
  void timeSecondsToString() {
    int input = 70;
    String result = Day02.timeSecondsToString(input);
    String expected = "1 : 10";

    Assertions.assertEquals(expected, result);
    ConsoleOutput.println(String.format("%d seconds is  "
        + "%s", input, result));
  }
}