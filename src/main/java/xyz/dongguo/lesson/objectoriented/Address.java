package xyz.dongguo.lesson.objectoriented;

import static xyz.dongguo.JsonHelper.isNotNullAndNotEmpty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import xyz.dongguo.JsonHelper;

/**
 * @author dongguo
 * @version 1.2
 */
public class Address {

  private String streetNumber;
  private String streetName;
  private String city;

  public Address(String streetNumber, String streetName, String city) {
    setStreetNumber(streetNumber);
    setStreetName(streetName);
    setCity(city);
  }

  /**
   * arrayList demo
   *
   * @param args any string array
   */
  public static void main(String[] args) {
    List<Address> addressList = createAddressList();
    String sainteAnne = addressList.get(0).streetName;
    List<Address> newList = addressList
       .stream()
       .filter(address -> address.streetName.equals(sainteAnne))
       .collect(Collectors.toList());
    newList.forEach(JsonHelper::printAllJson);
    long count = 0;
    for (Address currentAddress : addressList) {
      if (sainteAnne.equals(currentAddress.getStreetName())) {
        count++;
      }
    }
    System.out.printf("There are %d addresses whose street name is  %s", count, sainteAnne);
  }

  /**
   * Create data for demo
   *
   * @return A list including several mock addresses
   */
  public static List<Address> createAddressList() {
    String city = "Sainte-Anne-de-Bellevue";
    return new ArrayList<>(
       List.of(new Address("32b", "Sainte-Anne St", city),
          new Address("21275", "Lakeshore Dr", city),
          new Address("2", "Sainte-Anne St", city),
          new Address("21300", "Lakeshore Dr", city),
          new Address("1", "Maple St", city)
       ));
  }

  public String getStreetNumber() {
    return streetNumber;
  }

  public void setStreetNumber(String streetNumber) {
    validateInput(streetNumber);
    this.streetNumber = streetNumber;
  }

  /**
   * Throw IllegalArgumentException if input is null or empty.
   *
   * @param input a string
   */
  private void validateInput(String input) {
    if (!isNotNullAndNotEmpty(input)) {
      throw new IllegalArgumentException("Invalid input.");
    }
  }

  public String getStreetName() {
    return streetName;
  }

  public void setStreetName(String streetName) {
    validateInput(streetName);
    this.streetName = streetName;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    validateInput(city);
    this.city = city;
  }

  /**
   * @return A JsonHelper string
   */
  public String toJsonString() {
    return String.format("{\"address\" :  \"%s %s, %s\"}", streetNumber, streetName, city);
  }

  @Override
  public int hashCode() {
    return Objects.hash(streetNumber, streetName, city);
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
    return streetNumber.equals(address.streetNumber) && streetName.equals(address.streetName) && city.equals(
       address.city);
  }

  /**
   * @return A default string Created by IDE generator
   */
  @Override
  public String toString() {
    return "Address{" +
       "number='" + streetNumber + '\'' +
       ", street='" + streetName + '\'' +
       ", city='" + city + '\'' +
       '}';
  }
}
