package xyz.dongguo.lesson.objectoriented;

import java.util.ArrayList;
import java.util.List;
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

  public static List<Phone> createPhoneList() {
    return new ArrayList<>(List.of(
       new Phone("514813001"),
       new Phone("514813022"),
       new Phone("514813033"),
       new Phone("514814043"),
       new Phone("514813055"),
       new Phone("5148867788")));
  }

  public String toJsonString() {
    return String.format("{\"phoneNumber\" :  \"%s\"}", number);
  }

  @Override
  public int hashCode() {
    return Objects.hash(number, isActive);
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
  public String toString() {
    return "Phone{" +
       "number='" + number + '\'' +
       ", isActive=" + isActive +
       '}';
  }
}
