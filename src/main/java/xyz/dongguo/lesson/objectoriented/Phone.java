package xyz.dongguo.lesson.objectoriented;

import java.util.Objects;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Phone phone = (Phone) o;
    return isActive == phone.isActive && Objects.equals(number, phone.number);
  }

  @Override
  public int hashCode() {
    return Objects.hash(number, isActive);
  }
}
