package xyz.dongguo.lesson.basic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import xyz.dongguo.lesson.basic.Day07JobInterview;

class Day07JobInterviewTest {

  @Test
  void display() {
    Day07JobInterview.main();
    assertTrue(true);
  }

  @Test
  void askQuestions() {
    Day07JobInterview.askQuestions();
    assertTrue(true);

  }

  @Test
  void rob() {
    int[] inputs = {114, 117, 207, 117, 235, 82, 90, 67, 143, 146, 53, 108, 200, 91, 80, 223, 58, 170, 110, 236, 81, 90,
       222, 160, 165, 195, 187, 199, 114, 235, 197, 187, 69, 129, 64, 214, 228, 78, 188, 67, 205, 94, 205, 169, 241,
       202, 144, 240};
    int expected = 4173;
    int result = Day07JobInterview.robHouseUsingLoop(inputs);

    assertEquals(expected, result);
  }

  @Test
  void rob2() {
    int[] inputs = {114, 117, 207, 117, 235, 82, 90, 67, 143, 146, 53, 108, 200, 91, 80, 223, 58, 170, 110, 236, 81, 90,
       222, 160, 165, 195, 187, 199, 114, 235, 197, 187, 69, 129, 64, 214, 228, 78, 188, 67, 205, 94, 205, 169, 241,
       202, 144, 240};
    int expected = 4173;
    int result = Day07JobInterview.robeHouseUsingRecursion(inputs);

    assertEquals(expected, result);
  }
}
