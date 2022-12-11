package xyz.dongguo.lesson.objectoriented;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PersonTest {

  private String randomString;
  private List<Person> roster;
  private Person person;
  private String name;

  @BeforeEach
  void initEach() {
    randomString = "CapitalFirstName";
    roster = Person.createPeopleList();
    person = roster.get(0);
    name = person.getName();
  }

  //  @Disabled("just show the compile error")
  //  void createPersonWithoutNameReturnFail() {
  //    try {
  //      Person person = new Person();
  //    } catch (Error e) {
  //      assertTrue(true);
  //    }
  //  }
  @Test
  void printConsole() {
    String[] args = new String[3];
    Person.main(args);
    assertTrue(true);

  }

  @Test
  void createPersonWithInvalidName() {
    String emptyString = "";
    assertThrows(IllegalArgumentException.class, () -> new Person(emptyString));

  }

  @Test
  void createPersonOnlyWithName() {
    Person personWithDefaultValue = null;
    try {
      personWithDefaultValue = new Person(randomString);
    } catch (IllegalArgumentException e) {
      System.err.println(e.getMessage());
    }
    assertNotNull(personWithDefaultValue);
    assertNotNull(personWithDefaultValue.getId());
    assertNotNull(personWithDefaultValue.getName());
    assertNotNull(personWithDefaultValue.getKids());
    String phoneNumber = personWithDefaultValue.getPhone();
    assertTrue(phoneNumber.isEmpty());
    assertNull(personWithDefaultValue.getAddress());
    assertNull(personWithDefaultValue.getBirthDate());
    assertNull(personWithDefaultValue.getGender());

    assertEquals(randomString, personWithDefaultValue.getName());
    assertNotEquals(randomString, personWithDefaultValue.getId());
    assertEquals(-1, personWithDefaultValue.getAge());
    assertEquals(0, personWithDefaultValue.getKids().size());
  }

  @Test
  void createRoster() {
    assertNotNull(roster);
    assertFalse(roster.isEmpty());
  }

  @Test
  void getId() {
    assertNotNull(person.getId());
  }

  @Test
  void addKid() {
    Person kid1 = roster.get(1);
    person.addKid(kid1);
    assertEquals(kid1, person.getKids().get(0));
  }

  @Test
  void getBirthDate() {
    assertNull(person.getBirthDate());
    person.setAge(50);
    assertNotNull(person.getBirthDate());
  }

  @Test
  void setBirthDate() {
    String birthDateString = "2000-01-02";
    person.setBirthDate(birthDateString);
    assertEquals(birthDateString, person.getBirthDate());
    String wrongBirthDateString = "2001-02-29";
    person.setBirthDate(wrongBirthDateString);
    assertNotEquals(wrongBirthDateString, person.getBirthDate());
    assertEquals(birthDateString, person.getBirthDate());
  }

  @Test
  void isOrderThan() {
    Person kid1 = roster.get(1);
    assertFalse(person.isOrderThan(kid1));

    person.setAge(50);
    kid1.setAge(3);
    assertTrue(person.isOrderThan(kid1));
    assertFalse(kid1.isOrderThan(person));
    assertFalse(person.isOrderThan(person));
  }

  @Test
  void getName() {
    assertEquals(name, person.getName());
  }

  @Test
  void getAddress() {
    Person kid1 = roster.get(1);
    assertEquals(person.getAddress(), kid1.getAddress());
  }

  @Test
  void setAddress() {
    Address expectedAddress = new Address(randomString, randomString, randomString);
    person.setAddress(expectedAddress);
    Address newAddress = person.getAddress();
    assertEquals(expectedAddress, newAddress);
  }

  @Test
  void getKids() {
    List<Person> kids = person.getKids();
    assertNotNull(kids);
    assertEquals(0, kids.size());
  }

  @Test
  void getGender() {
    assertNotNull(person.getGender());
  }

  @Test
  void setGender() {
    SexEnum male = SexEnum.MALE;
    person.setGender(male);
    assertEquals(male, person.getGender());
  }

  @Test
  void getPhone() {
    assertNotNull(person.getPhone());
  }

  @Test
  void setPhone() {
    String phoneString = "5148138888";
    assertNotNull(person.getPhone());
    person.setPhone(phoneString);
    assertEquals(phoneString, person.getPhone());
    String wrongPhoneString = "quebec001";
    person.setPhone(wrongPhoneString);
    assertNotEquals(wrongPhoneString, person.getPhone());
    assertEquals(phoneString, person.getPhone());
  }

  @Test
  void toJsonString() {
    String jsonString = person.toJsonString();
    assertNotNull(jsonString);
    assertFalse(jsonString.isEmpty());
    assertEquals("{", jsonString.substring(0, 1));
  }

  @Test
  void getAge() {
    assertEquals(-1, person.getAge());
  }

  @Test
  void setAge() {
    int age = 50;
    person.setAge(age);
    assertEquals(age, person.getAge());
  }

  @Test
  void testEquals() {
    Person duplicatedPerson = roster.get(0);
    duplicatedPerson.setBirthDate("2022-10-01");
    Person kid1 = roster.get(1);
    assertEquals(person, duplicatedPerson);
    assertNotEquals(person, kid1);
  }

  @Test
  void testHashCode() {
    int hasCode = person.hashCode();
    assertTrue(true);
  }

}
