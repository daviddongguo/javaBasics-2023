package xyz.dongguo.lesson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class DongguoTest {

  @Test
  void printOccurrencesOfEachNumber() {
    int[] arr = {9, 1, 1, 5};
    Dongguo.printOccurrencesOfEachNumber(arr);
    assertTrue(true);
  }

  @Test
  void findSecondLargest() {
    int[] arr = {9, 9, 9, 5, 9, 4, 4};
    int result = Dongguo.findSecondLargest(arr);
    assertEquals(5, result);
  }

  @Test
  void testEqualsReturnFalse() {
    int[] arr1 = {9, 9, 9, 5, 9, 4, 4};
    int[] arr2 = {9, 9, 9, 5, 9, 3, 4};
    boolean result = Dongguo.equals(arr1, arr2);
    assertFalse(result);
  }
  @Test
  void testEquals() {
    int[] arr1 = {9, 9, 9, 5, 9, 4, 4};
    int[] arr2 = {9, 9, 9, 5, 9, 4, 4};
    boolean result = Dongguo.equals(arr1, arr2);
    assertTrue(result);
  }
  @Test
  void sumColumn() {
    assertTrue(true);
  }
}