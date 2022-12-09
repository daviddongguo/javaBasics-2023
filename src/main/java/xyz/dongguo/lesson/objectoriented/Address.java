package xyz.dongguo.lesson.objectoriented;

import java.util.Objects;

/**
 * @author map
 */
public class Address {

  String number;
  String street;
  String city;

  public Address(String number, String street, String city) {
    this.number = number;
    this.street = street;
    this.city = city;
  }

  @Override
  public String toString() {
    return toJsonString();
  }

  public String toJsonString() {
    return String.format("{\"address\" :  \"%s, %s, %s\"}", number, street, city);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Address address = (Address) o;
    return number.equals(address.number) && street.equals(address.street) && city.equals(address.city);
  }

  @Override
  public int hashCode() {
    return Objects.hash(number, street, city);
  }
}
