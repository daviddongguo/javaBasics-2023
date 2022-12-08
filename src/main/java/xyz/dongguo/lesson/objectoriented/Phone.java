package xyz.dongguo.lesson.objectoriented;

/**
 * @author map
 */
public class Phone {

  String number;
  boolean isActive = true;

  public Phone(String number) {
    this.number = number;
  }

  public String toJsonString() {
    return String.format("{\"phoneNumber\" :  \"%s\"}", number);
  }

  @Override
  public String toString() {
    return toJsonString();
  }
}
