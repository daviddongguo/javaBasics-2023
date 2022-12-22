package xyz.dongguo.lesson.basic;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author dongguo
 * @date 2022-12-04
 */
public class Day07JobInterview {

  /**
   * Display five Problems.
   */
  public static void main() {
    String stringFormatter = "output: %s%n%n";

    System.out.println("problem 01 Two sum");
    int[] numbers = {1, 2, 3, 4, 5, 6, 7};
    int target = 8;
    System.out.printf("Input: nums = %s, target=%d%n", Arrays.toString(numbers), target);
    int[] output = sumTwoByBruteForce(numbers, 6);
    System.out.printf(stringFormatter, Arrays.toString(output));

    System.out.println("problem 02 Exist Sum");
    int[] array = {1, 3, 4, 5, 6};
    int sum = 7;
    System.out.printf("Input:  arr[]  = %s,  sum =%d%n", Arrays.toString(array), sum);
    System.out.printf(stringFormatter, isSumExisted(array, sum));

    System.out.println("problem 03 House Robber");
    System.out.printf("Input: nums = %s%n", Arrays.toString(numbers));
    System.out.printf(stringFormatter, robHouseUsingLoop(numbers));

    System.out.println("problem 04 Rotate Array");
    int steps = 3;
    System.out.printf("Input: nums = %s, step= %d%n", Arrays.toString(numbers), steps);
    rotate(numbers, steps);
    System.out.printf(stringFormatter, Arrays.toString(numbers));

    System.out.println("problem 05 Remove duplicates from Sorted Array");
    int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
    System.out.printf("Input: nums = %s%n", Arrays.toString(nums));
    int kind = removeDuplicates(nums);
    System.out.printf("output: k = %d, %s%n%n", kind, Arrays.toString(nums));

  }
  /**
   * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to
   * target. You may assume that each input would have exactly one solution, and you may not use the same element twice.
   * You can return the answer in any order.
   * <p>
   * Use a hash table to solve this problem  in linear time.
   * The idea is to insert each array element's difference(nums[i], target - nums[i]) into a map.
   * We also check if nums[i] already exists in the map or not.
   * if the nums[i] is seen before, return the value.
   * <p>
   * The time complexity of the solution is O(n) extra space, where n is the size of the input.
   * @param nums   An array of integers
   * @param target An integer
   * @return Indices of the two numbers such that they add up to target
   */
  public static int[] isSumTwoExistedUsingHasing(int[] nums, int target) {
    HashMap<Integer, Integer> differenceMap = new HashMap<>(nums.length);
    for (int i = 0; i < nums.length; i++) {
      if(differenceMap.containsKey(i)){
        return new int[]{i, differenceMap.get(i)};
      }
      int difference = target - nums[i];
      differenceMap.put(difference, i);
    }

    return new int[0];
  }

  /**
   * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to
   * target. You may assume that each input would have exactly one solution, and you may not use the same element twice.
   * You can return the answer in any order.
   * A naive solution is to consider every pair in the given array and return if the desired sum is found.
   *
   * @param nums   An array of integers
   * @param target An integer
   * @return Indices of the two numbers such that they add up to target
   */
  public static int[] sumTwoByBruteForce(int[] nums, int target) {
    // nested loop without the duplicated element
    for (int i = 0; i < nums.length - 1; i++) {
      for (int j = i + 1; j < nums.length; j++) {
        if (nums[i] + nums[j] == target) {
          return new int[]{i, j};
        }
      }
    }

    return new int[0];
  }

  /**
   * Given an array of integers nums and an integer target, return true if there exist any numbers in the array whose
   * sum is exactly sum, otherwise false.
   *
   * @param array An array of integer.
   * @param sum   An integer number.
   * @return true if there exist two numbers in the array whose sum is exactly sum, otherwise false.
   */
  public static boolean isSumExisted(int[] array, int sum) {
    return isSumExisted(array, 0, sum);
  }

  /**
   * Given an array of integers nums and an integer target, return true if there exist any numbers in the array whose
   * sum is exactly sum, otherwise false.
   *
   * @param array An array of integer.
   * @param from  left index of the array.
   * @param sum   An integer number.
   * @return true if there exist two numbers in the array whose sum is exactly sum, otherwise false.
   */
  public static boolean isSumExisted(int[] array, int from, int sum) {
    if (from >= array.length) {
      return false;
    }
    if (from == array.length - 1) {
      return sum == array[array.length - 1];
    }
    return isSumExisted(array, from + 1, sum)
       || isSumExisted(array, from + 1, sum - array[from]);
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
  public static int robHouseUsingLoop(int[] nums) {
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

  public static int robeHouseUsingRecursion(int[] nums) {
    if (nums == null) {
      return 0;
    }
    if (nums.length <= 1) {
      return nums[0];
    }
    int[] maxes = new int[nums.length];

    return robeHouseUsingRecursion(nums, 0, maxes);
  }

  public static int robeHouseUsingRecursion(int[] nums, int from, int[] maxes) {
    // Termination condition
    if (from >= nums.length) {
      return 0;
    }
    if (from == nums.length - 1) {
      return nums[nums.length - 1];
    }

    // check the cache
    if (maxes[from] > 0) {
      return maxes[from];

    }

    // Separate to smaller problem
    int max = Math.max(
       (robeHouseUsingRecursion(nums, from + 2, maxes) + nums[from]),
       robeHouseUsingRecursion(nums, from + 1, maxes));
    // add to the cache
    maxes[from] = max;
    return max;
  }

  /**
   * Given an array, rotate the array to the right by k steps when  k > 0 rotate the array to the left when  k <  0
   *
   * @param arr   An array of integers.
   * @param steps The steps will rotate.
   */
  public static void rotate(int[] arr, int steps) {
    // validate
    if (arr == null) {
      return;
    }
    int size = arr.length;
    if (size <= 1 || steps % size == 0) {
      return;
    }

    // 1 <= standardSteps < 2 * size
    int standardSteps = steps % size + size;
    int[] arrRotated = new int[size];
    for (int i = 0; i < size; i++) {
      int index = (i + standardSteps) % size;
      arrRotated[index] = arr[i];
    }

    System.arraycopy(arrRotated, 0, arr, 0, size);
  }

  /**
   * Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique
   * element appears only once. The relative order of the elements should be kept the same.
   *
   * @param numbers An integer array
   * @return The number of unique element
   */
  public static int removeDuplicates(int[] numbers) {
    if (numbers == null || numbers.length < 1) {
      return 0;
    }
    if (numbers.length == 1) {
      return 1;
    }

    int left = 0;
    int right = 1;
    while (right <= numbers.length - 1) {
      if (numbers[left] != numbers[right]) {
        left++;
        numbers[left] = numbers[right];
      }
      right++;
    }

    return left + 1;
  }

  private Day07JobInterview() {
  }

  /**
   * Does Java code really write once and run anywhere?
   * <p>
   * No. the same Java byte code can run on any Java Virtual Machine. But the JVM has to be rewritten to work on
   * different operating systems. So run anywhere only works if the Java Virtual Machine exists for every machine. Our
   * java code may write once but the JVM needs to be written anywhere. On other hand, based on the "Virtual Machine" or
   * Container, almost any programming languages can write once, run anywhere, even some java program also be deployed
   * on the container for better isolated from the host operating system.
   * <p>
   * Does the Java method have default parameters?
   * <p>
   * No, Java does not support assigning a default value to a method parameter. But we can simulate default parameters
   * with several ways. overloading is the cleanest and simple, easy way.  With method overloading, multiple methods can
   * have the same name with different parameters. So we can create a same name method without parameters that call
   * itself with the default value. I know another way is allowing null as method parameters.
   * <p>
   * Why not use Double or Float to represent Money? Because float and double is base 2 floating-point data types, and
   * money needs the base 10 data type. the former can not exactly represent the latter. Actually, only like 0.25, 0.5,
   * 0.75 these  numbers that are powers of 2 can be represented exactly. All the others are close to the real when
   * converted to binary floating-point. For example, the decimal number 0.1 is an infinite sequence of binary. It can
   * not be represented in any finite precision. Luckily, Java has the BigDecimal class to deal with money. For me,
   * sometimes I prefer to use integers instead and count cents.
   * <p>
   * Where is a Java string stored? It depends. Depends on how the java string has been defined. A string literal by
   * assigning a value (like any other primitive datatype) is in the string constant pool under heap memory. A String
   * object we create by using the new keyword (like any other object) is in the heap memory. Of course, this parameter
   * as a string literal is stored in the string constant pool.  There two references, one is point to the object, one
   * is point the string literal, we should be careful when we compare two strings. Should the nested loops be avoided?
   * <p>
   * It depends. sometimes I prefer to nested loops for they are so highly readable that we will quickly understand what
   * is doing.  readability is very important when writing code. On another hand, It is true that nested loops kill much
   * time to run and especially that time grows up as the square of the number of things you run it on. That’s why it’s
   * bad. It gets slow quickly. we should be careful with nested loops since they will consume much more compute power.
   * and especially we should avoid to use other resources like I/0, Net , Database, memory and so on  in a nested loops
   * even a loop.
   */
  public static void askQuestions() {
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
