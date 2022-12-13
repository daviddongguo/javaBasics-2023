package xyz.dongguo.lesson.objectoriented;

/**
 * @author dongg
 */
public class Day08 {

  public static void main(String[] args) {

    printAll("123", 123, Boolean.TRUE);

  }

  public static void printAll(Object... args) {
    for (Object o : args) {
      System.out.println(o);
    }
  }

}
