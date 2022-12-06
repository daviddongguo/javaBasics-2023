package xyz.dongguo.lesson;

import java.util.Arrays;

/**
 * @author dongguo
 * @date 2022-12-04
 */
public class Day07JobInterview {

  public void displayProblems() {
    System.out.println("problem 01 Two sum");
    int[] numbers = {1, 2, 3, 4, 5, 6, 7};
    int target = 8;
    System.out.printf("Input: nums = %s, target=%d%n", Arrays.toString(numbers), target);
    int[] output = twoSum(numbers, 6);
    System.out.printf("output: %s%n%n", Arrays.toString(output));

    System.out.println("problem 02 Exist Sum");
    int[] array = {1, 3, 4, 5, 6};
    int sum = 7;
    System.out.printf("Input:  arr[]  = %s,  sum =%d%n", Arrays.toString(array), sum);
    System.out.printf("output: %s%n%n", isSumExisted(array, sum));

    System.out.println("problem 03 House Robber");
    System.out.printf("Input: nums = %s%n", Arrays.toString(numbers));
    System.out.printf("output: %d%n%n", robHouseUsingLoop(numbers));

    System.out.println("problem 04 Rotate Array");
    int steps = 3;
    System.out.printf("Input: nums = %s, step= %d%n", Arrays.toString(numbers), steps);
    rotate(numbers, steps);
    System.out.printf("output: %s%n%n", Arrays.toString(numbers));

    System.out.println("problem 05 Remove duplicates from Sorted Array");
    int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
    System.out.printf("Input: nums = %s%n", Arrays.toString(nums));
    int kind = removeDuplicates(nums);
    System.out.printf("output: k = %d, %s%n%n", kind, Arrays.toString(nums));

  }

  /**
   * Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique
   * element appears only once. The relative order of the elements should be kept the same.
   *
   * @param numbers An integer array
   * @return The number of unique element
   */
  private int removeDuplicates(int[] numbers) {
    if (numbers == null || numbers.length < 1) {
      return 0;
    }
    if (numbers.length == 1) {
      return 1;
    }

    int left = 0;
    int right = 1;
    while (right <= numbers.length - 1) {
      if (numbers[left] == numbers[right]) {
        right++;
      } else {
        left++;
        numbers[left] = numbers[right];
        right++;
      }
    }

    return left + 1;
  }

  /**
   * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to
   * target. You may assume that each input would have exactly one solution, and you may not use the same element twice.
   * You can return the answer in any order.
   *
   * @param nums   An array of integers
   * @param target An integer
   * @return Indices of the two numbers such that they add up to target
   */
  public int[] twoSum(int[] nums, int target) {
    int[] results = {-1, -1};
    // nested loop
    for (int i = 0; i < nums.length - 1; i++) {
      for (int j = i + 1; j < nums.length; j++) {
        if (nums[i] + nums[j] == target) {
          results[0] = i;
          results[1] = j;
          return results;
        }
      }
    }

    return results;
  }

  /**
   * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money
   * stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems
   * connected , and it will automatically contact the police if two adjacent houses were broken into on the same night
   * Given an integer array nums representing the amount of money of each house, return the maximum amount of money you
   * can rob tonight without alerting the police.
   *
   * @param nums represent the amount of money of each house,
   * @return the maximum amount of money you can rob without robbing two adjacent houses
   */
  public int robHouseUsingLoop(int[] nums) {
    // Valid
    if (nums == null) {
      return 0;
    }
    int size = nums.length;
    if (size <= 1) {
      return nums[0];
    }

    // The first  house
    int rob = nums[0];
    int noRob = 0;
    int max = Math.max(rob, noRob);

    // From the second house
    for (int i = 1; i < size; i++) {
      rob = nums[i] + noRob;
      noRob = max;
      max = Math.max(rob, noRob);
    }

    return max;
  }

  /**
   * Given an array of integers nums and an integer target, return true if there exist two numbers in the array whose
   * sum is exactly sum, otherwise false.
   *
   * @param array An array of integer.
   * @param sum   An integer number.
   * @return true if there exist two numbers in the array whose sum is exactly sum, otherwise false.
   */
  private static boolean isSumExisted(int[] array, int sum) {
    return isSumExisted(array, 0, sum);
  }

  /**
   * Given an array of integers nums and an integer target, return true if there exist two numbers in the array whose
   * sum is exactly sum, otherwise false.
   *
   * @param array An array of integer.
   * @param left  left index of the array.
   * @param sum   An integer number.
   * @return true if there exist two numbers in the array whose sum is exactly sum, otherwise false.
   */
  private static boolean isSumExisted(int[] array, int left, int sum) {
    if (left >= array.length) {
      return false;
    }
    if (left == array.length - 1) {
      return sum == array[array.length - 1];
    }
    return isSumExisted(array, left + 1, sum)
       || isSumExisted(array, left + 1, sum - array[left]);
  }

  /**
   * Given an array, rotate the array to the right by k steps when  k > 0 rotate the array to the left when  k <  0
   *
   * @param arr   An array of integers.
   * @param steps The steps will rotate.
   */
  private void rotate(int[] arr, int steps) {
    // validate
    if (arr == null || arr.length <= 1 || steps % arr.length == 0) {
      return;
    }

    int standardSteps = steps % arr.length + arr.length;
    int[] swapped = new int[arr.length];
    for (int i = 0; i < arr.length; i++) {
      int index = (i + standardSteps) % arr.length;
      swapped[index] = arr[i];
    }

    for (int i = 0; i < arr.length; i++) {
      arr[i] = swapped[i];
    }
  }

  public int robeHouseUsingRecursion(int[] nums) {
    if (nums == null) {
      return 0;
    }
    if (nums.length <= 1) {
      return nums[0];
    }
    int[] maxes = new int[nums.length];

    return robeHouseUsingRecursion(nums, 0, maxes);
  }

  private int robeHouseUsingRecursion(int[] nums, int left, int[] maxes) {

    if (left >= nums.length) {
      return 0;
    }
    if (left == nums.length - 1) {
      return nums[nums.length - 1];
    }
    if (maxes[left] > 0) {
      return maxes[left];

    }
    int max = Math.max((nums[left] + robeHouseUsingRecursion(nums, left + 2, maxes)),
       robeHouseUsingRecursion(nums, left + 1, maxes));
    maxes[left] = max;
    return max;
  }

  public void askQuestions() {
    String question1 = "Does Java code really write once and run anywhere?";
    String question2 = "Does the Java method have default parameters?";
    String question3 = "Why not use Double or Float to represent Money?";
    String question4 = "Where is a Java string stored?";
    String question5 = "Should nested loops be avoided?";
    System.out.println(question1);
    System.out.println(question2);
    System.out.println(question3);
    System.out.println(question4);
    System.out.println(question5);
  }

}
