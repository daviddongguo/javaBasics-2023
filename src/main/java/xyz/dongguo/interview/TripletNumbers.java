package xyz.dongguo.interview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TripletNumbers {

  public static void main(String[] args) {

    System.out.println("hello");

    int[] input = {1, 4, 7, 4, 1, 1};
    System.out.println(f(input));




  }

  public static boolean f(int[] nums){
    Map<Integer, Integer> map = new HashMap<>(nums.length);
    for (int number: nums){
      if(map.containsKey(number)){
        map.put(number, map.get(number) + 1);
      }else {
        map.put(number, 1);
      }
    }
    List<Integer> values = new ArrayList<>(map.values());

    for (int i = 0; i < values.size() - 1; i++) {
      for (int j = i + 1; j < values.size(); j++) {
        if(values.get(i).equals( values.get(j)) ){
          return false;
        }
      }
    }
    return true;

  }

}

