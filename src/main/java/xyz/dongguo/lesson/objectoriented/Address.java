package xyz.dongguo.lesson.objectoriented;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author map
 */
public class Address implements Jsonable {

  String number;
  String street;
  String city;

  public Address(String number, String street, String city) {
    this.number = number;
    this.street = street;
    this.city = city;
  }

  /**
   * arrayList demo
   *
   * @param args any string array
   */
  public static void main(String[] args) {
    List<Address> addressList = createAddressList();
    String sainteAnne = addressList.get(0).street;
    List<Address> newList = addressList
       .stream()
       .filter(address -> address.street.equals(sainteAnne))
       .collect(Collectors.toList());
    newList.forEach(Person::printPrettyJson);
    long count = addressList
       .stream()
       .filter(address -> address.street.equals(sainteAnne))
       .count();
    System.out.printf("There are %d addresses whose sainteAnne is  %s", count, sainteAnne);
  }

  /**
   * Create data for demo
   *
   * @return A list including several mock addresses
   */
  public static List<Address> createAddressList() {
    return new ArrayList<>(
       List.of(new Address("32b", "Sainte-Anne St", "Sainte-Anne-de-Bellevue"),
          new Address("21275", "Lakeshore Dr", "Sainte-Anne-de-Bellevue"),
          new Address("2", "Sainte-Anne St", "Sainte-Anne-de-Bellevue"),
          new Address("21300", "Lakeshore Dr", "Sainte-Anne-de-Bellevue"),
          new Address("1", "Maple St", "Sainte-Anne-de-Bellevue")
       ));
  }

  /**
   * @return A Json string
   */
  @Override
  public String toJsonString() {
    return String.format("{\"address\" :  \"%s, %s, %s\"}", number, street, city);
  }

  @Override
  public int hashCode() {
    return Objects.hash(number, street, city);
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

  /**
   * @return A default string Created by IDE generator
   */
  @Override
  public String toString() {
    return "Address{" +
       "number='" + number + '\'' +
       ", street='" + street + '\'' +
       ", city='" + city + '\'' +
       '}';
  }
}
