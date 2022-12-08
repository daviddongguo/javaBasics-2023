package xyz.dongguo.lesson.objectoriented;

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

}
