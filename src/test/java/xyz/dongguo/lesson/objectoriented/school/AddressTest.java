package xyz.dongguo.lesson.objectoriented.school;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.javafaker.Faker;
import java.util.List;
import java.util.Random;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AddressTest {

  private final Faker faker = new Faker(new Random(24));

  private Address address;
  private String streetNumber;
  private String city;
  private String streetName;
  private String randomStreetName;

  @BeforeEach
  void setupEach() {
    var fakerAddress = faker.address();
    streetNumber = fakerAddress.streetAddressNumber();
    streetName = fakerAddress.streetName();
    city = fakerAddress.city();
    address = new Address(streetNumber, streetName, city);
    randomStreetName = (new Faker(new Random(24))).address().streetName();
  }

  @Test
  void main() {
    Address.main(new String[1]);
    assertTrue(true);
  }

  @Test
  void createAddressList() {
    List<Address> list = Address.createAddressList();
    assertFalse(list.isEmpty());
  }

  @Test
  void getStreetNumber() {
    assertEquals(streetNumber, address.getStreetNumber());
  }

  @Test
  void setStreetNumber() {
    address.setStreetNumber(randomStreetName);
    assertEquals(randomStreetName, address.getStreetNumber());
  }

  @Test
  void getStreetName() {
    assertEquals(streetNumber, address.getStreetNumber());
  }

  @Test
  void setStreetName() {
    address.setStreetNumber(randomStreetName);
    assertEquals(streetName, address.getStreetName());
  }

  @Test
  void getCity() {
    assertEquals(city, address.getCity());
  }

  @Test
  void setCity() {
    address.setCity(randomStreetName);
    assertEquals(randomStreetName, address.getCity());
  }

  @Test
  void toJsonString() {
    String json = address.toJsonString();
    JSONObject obj = new JSONObject(json);
    String result = String.valueOf(obj.get("address"));
    assertTrue(result.contains(streetNumber));
  }
}