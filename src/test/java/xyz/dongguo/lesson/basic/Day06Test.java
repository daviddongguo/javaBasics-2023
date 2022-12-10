package xyz.dongguo.lesson.basic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import xyz.dongguo.lesson.basic.Day06;

class Day06Test {

  private final Day06 solutions = new Day06(System.out);

  @Test
  void printInput() {
    assertTrue(true);
  }

  @Test
  void isSumExisted() {
    int[] arr = {1, 4, 3, 4, 9};
    int sum = 8;

    System.out.println(solutions.isSumExisted(arr, 0, arr.length - 1, sum));
    assertTrue(true);
  }

  @Test
  void getGreatestDivisor() {
    int result = solutions.getGreatestDivisor(20, 11);
    assertEquals(1, result);
    result = solutions.getGreatestDivisor(20, 10);
    assertEquals(10, result);
    result = solutions.getGreatestDivisor(125, 75);
    assertEquals(25, result);
  }

  @ParameterizedTest
  @ValueSource(ints =
     {4, 6, 8, 9, 10, 12, 14, 15, 16, 880}
  )
  void isPrimeReturnFalse(int number) {
    assertFalse(solutions.isPrime(number));
  }

  @ParameterizedTest
  @ValueSource(ints =
     {1, 2, 3, 5, 7, 11, 13, 17, 19, 23,
        29, 31, 37, 41, 43, 47, 53, 59, 61, 67,
        71, 73, 79, 83, 89, 97, 101, 103, 107, 109,
        113, 127, 131, 137, 139, 149, 151, 157, 163, 167,
        173, 179, 181, 191, 193, 197, 199, 211, 223, 227}
  )
  void isPrimeReturnTrue(int number) {
    assertTrue(solutions.isPrime(number));
  }

  @Test
  void countOccurrenceOfLetter() {
    int[] results = solutions.countOccurrenceOfLetter("");
    for (int i : results) {
      assertEquals(0, i);
    }
  }

  @Test
  void countOccurrenceOfLetterWhenEmptyString() {
    int[] results = solutions.countOccurrenceOfLetter("");
    for (int i : results) {
      assertEquals(0, i);
    }
  }

}