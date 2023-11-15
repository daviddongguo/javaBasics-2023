package xyz.dongguo.interview;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FindPattern {

  public static void main(String[] args){
    System.out.println("hi, there");

    String pattern = "aabb";
    String str = "cat cat dong dong";

    String[] array = str.split(" ");
    Set<String> set = new HashSet<>(Arrays.asList(array));
    int size = set.size();
    String stringPattern = "abcdefghigklmn".substring(0, size);
    for (int i = 0; i < size; i++) {
      String s = stringPattern.substring(i, i+1);
      for (int j = 0; j < str.length(); j++) {
        
        
      }
      
    }




  }

}
