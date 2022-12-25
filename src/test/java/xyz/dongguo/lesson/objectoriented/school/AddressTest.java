package xyz.dongguo.lesson.objectoriented.school;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static xyz.dongguo.lesson.objectoriented.school.JsonHelper.generateRandomString;

import java.util.List;
import java.util.Random;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import xyz.dongguo.lesson.objectoriented.school.Address;

class AddressTest {

  private Address address;
  private String streetNumber;
  private String city;
  private String streetName;
  private String randomStr;

  @BeforeEach
  void setupEach() {
    streetNumber = "123";
    streetName = "Hymus Blvd";
    city = "Montreal";
    address = new Address(streetNumber, streetName, city);
    randomStr = generateRandomString(new Random(), 15);
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
    address.setStreetNumber(randomStr);
    assertEquals(randomStr, address.getStreetNumber());
  }

  @Test
  void getStreetName() {
    assertEquals(streetNumber, address.getStreetNumber());
  }

  @Test
  void setStreetName() {
    address.setStreetNumber(randomStr);
    assertEquals(streetName, address.getStreetName());
  }

  @Test
  void getCity() {
    assertEquals(city, address.getCity());
  }

  @Test
  void setCity() {
    address.setCity(randomStr);
    assertEquals(randomStr, address.getCity());
  }

  @Test
  void toJsonString() {
    String json = address.toJsonString();
    JSONObject obj = new JSONObject(json);
    String result = String.valueOf(obj.get("address"));
    assertTrue(result.contains(streetNumber));
  }
}