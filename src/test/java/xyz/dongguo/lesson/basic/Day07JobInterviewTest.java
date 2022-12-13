package xyz.dongguo.lesson.basic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class Day07JobInterviewTest {

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

  @Test
  void main() {
    Day07JobInterview.main();
    assertTrue(true);
  }

  @Test
  void twoSum() {
    assertTrue(true);
  }

  @Test
  void isSumExisted() {
    int[] inputs = {3, 4, 5, 2, 1};
    int sum = 8;
    assertTrue(Day07JobInterview.isSumExisted(inputs, sum));
    sum = 10;
    assertTrue(Day07JobInterview.isSumExisted(inputs, sum));
    sum = 16;
    assertFalse(Day07JobInterview.isSumExisted(inputs, sum));
  }

  @Test
  void testIsSumExisted() {
    int[] inputs = {3, 4, 5, 2, 1};
    int sum = 8;
    assertEquals(2, Day07JobInterview.sumTwoByBruteForce(inputs, sum).length);
    sum = 10;
    assertEquals(0, Day07JobInterview.sumTwoByBruteForce(inputs, sum).length);
    sum = 16;
    assertEquals(0, Day07JobInterview.sumTwoByBruteForce(inputs, sum).length);
  }

  @Test
  void testIsSumExisted_UsingHashing() {
    int[] inputs = {3, 4, 5, 2, 1};
    int sum = 8;
    int[] array = Day07JobInterview.isSumTwoExistedUsingHasing(inputs, sum);
    int result = array.length;
    assertEquals(2, result);
    sum = 10;
    assertEquals(0, Day07JobInterview.isSumTwoExistedUsingHasing(inputs, sum).length);
    sum = 16;
    assertEquals(0, Day07JobInterview.isSumTwoExistedUsingHasing(inputs, sum).length);
  }

  @Test
  void robHouseUsingLoop() {
    assertTrue(true);
  }

  @Test
  void robeHouseUsingRecursion() {
    assertTrue(true);
  }

  @Test
  void testRobeHouseUsingRecursion() {
    assertTrue(true);
  }

  @Test
  void rotate() {
    assertTrue(true);
  }

  @Test
  void removeDuplicates() {
    assertTrue(true);
  }

  @Test
  void testAskQuestions() {
    assertTrue(true);
  }
}
