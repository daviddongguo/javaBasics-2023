package xyz.dongguo.interview;

import java.util.HashMap;
import java.util.Map;

public class DegreeArray {

  public static void main(String[] args) {
    System.out.println("hi, there");

    int[] nums = {1, 2, 3, 4, 5};
    int[] nums2 = {1, 2, 2, 3, 1, 4, 2};

    var map = createMap(nums);
    int sum = 0;
    for (var entry : map.entrySet()) {
      if (entry.getValue() == 1) {
        sum += entry.getKey();
      }
    }

    System.out.println(sum);

  }

  public static Map<Integer, Integer> createMap(int[] nums) {
    Map<Integer, Integer> map = new HashMap<>(nums.length);
    for (int number : nums) {
      if (map.containsKey(number)) {
        map.put(number, map.get(number) + 1);
      } else {
        map.put(number, 1);
      }
    }

    return map;
  }

  public static int findShortestSubArray(int[] nums, int degree) {
    int leftIndex = 0;
    int rightIndex = nums.length - 1;
    while (nums[leftIndex] != degree || nums[rightIndex] != degree) {
      if (nums[leftIndex] != degree) {
        leftIndex++;
      }
      if (nums[rightIndex] != degree) {
        rightIndex--;
      }
    }

    return rightIndex - leftIndex + 1;
  }

  public static int findIndexOfDegree(int[] nums, int degree) {

    Map<Integer, Integer> map = createMap(nums);

    int max = 0;
    int indexOfMax = 0;
    for (var entry : map.entrySet()) {
      if (entry.getKey() != degree && entry.getValue() > max ) {
          max = entry.getValue();
          indexOfMax = entry.getKey();
      }
    }
    return indexOfMax;
  }

}
