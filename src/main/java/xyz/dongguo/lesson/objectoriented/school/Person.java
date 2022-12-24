package xyz.dongguo.lesson.objectoriented.school;

import static xyz.dongguo.lesson.objectoriented.school.JsonHelper.generateRandomString;
import static xyz.dongguo.lesson.objectoriented.school.JsonHelper.isNotNullAndNotEmpty;
import static xyz.dongguo.lesson.objectoriented.school.JsonHelper.printJson;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * The Person class representing a person.
 *
 * @author dongguo
 * @version 1.2
 */
public class Person {

  public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  /**
   * The start of the string matches any uppercase letter, no more than 20 characters
   */
  public static final int LENGTH_ID = 50;

  private final String id = generateRandomString(new Random(), LENGTH_ID);
  private final List<Person> kids = new ArrayList<>();
  private Address address;
  private String name;
  private SexEnum gender;
  private PhoneNumber phoneNumber;
  private LocalDate birthDate;

  public Person(String name, PhoneNumber phoneNumber, Address address, SexEnum gender) throws IllegalArgumentException {
    this(name, phoneNumber, address);
    this.setGender(gender);
  }

  public Person(String name, PhoneNumber phoneNumber, Address address) throws IllegalArgumentException {
    this(name);
    setPhone(phoneNumber);
    setAddress(address);
  }

  public Person(String name) throws IllegalArgumentException {
    this.setName(name);
  }

  /**
   * for debugging
   */
  public static void main(String[] args) {
    List<Person> peopleList = Person.createPeopleList();
    Person personAlice = peopleList.get(0);
    Person kidBob = peopleList.get(1);
    Person kidTom = peopleList.get(2);

    List<PhoneNumber> phoneNumberList = PhoneNumber.createPhoneList();
    PhoneNumber phoneNumber1 = phoneNumberList.get(0);
    PhoneNumber phoneNumber2 = phoneNumberList.get(1);
    PhoneNumber phoneNumber3 = phoneNumberList.get(2);

    personAlice.setPhone(phoneNumber1);
    kidBob.setPhone(phoneNumber2);
    kidTom.setPhone(phoneNumber3);

    List<Address> addressList = Address.createAddressList();
    personAlice.setAddress(addressList.get(0));
    kidBob.setAddress(addressList.get(1));
    kidTom.setAddress(addressList.get(2));

    personAlice.addKid(kidBob);
    personAlice.addKid(kidTom);

    // Add item to a list
    printJson(personAlice);
    List<Person> list = new ArrayList<>(7);
    Person personToTest = new Person("PersonToTest");
    list.add(personAlice);
    list.add(kidBob);
    list.add(kidTom);
    list.add(personToTest);
    System.out.printf("The list has %d people.%n", list.size());

    // Delete item from list
    Person personToRemove = null;
    for (Person currentPerson : list) {
      if (currentPerson.getName().equals(personToTest.getName())) {
        System.out.printf("Found %s%n", currentPerson.getName());
        personToRemove = currentPerson;
      }
    }
    list.remove(personToRemove);
    System.out.printf("The list has %d people.%n", list.size());

    // Find people  who live in the same city
    String city = "Sainte-Anne-de-Bellevue";
    for (Person p : list) {
      if (p.getAddress().getCity().equals(city)) {
        System.out.printf("Found %s who lives in  %s%n", p.getName(), city);
      }
    }

    // Update Bob who move to Quebec City
    Person personBob = null;
    Address newAddress = new Address("123", "Queen Street", "Quebec City");
    for (Person currentPerson : list) {
      if ("Bob".equals(currentPerson.getName())) {
        System.out.printf("%s who lives in  %s now%n", currentPerson.getName(), currentPerson.getAddress());
        personBob = currentPerson;
        break;
      }
    }
    assert personBob != null;
    personBob.setAddress(newAddress);
    System.out.printf("%s who lives in  %s now%n", personBob.getName(), personBob.getAddress());

  }

  /**
   * debugging data includes three people
   *
   * @return three people
   */
  public static List<Person> createPeopleList() {
    List<Person> peopleList = new ArrayList<>();
    Address address = new Address("123", "King Street", "Quebec City");
    PhoneNumber phoneNumber1 = new PhoneNumber("5145131234");
    PhoneNumber phoneNumber2 = new PhoneNumber("5145131212");

    try {
      peopleList.add(new Person("Alice", phoneNumber1, address, SexEnum.FEMALE));
      peopleList.add(new Person("Bob", null, address, SexEnum.MALE));
      peopleList.add(new Person("Tom", phoneNumber2, address, SexEnum.MALE));
    } catch (IllegalArgumentException e) {
      System.err.println(e.getMessage());
    }

    return peopleList;
  }

  public void addKid(Person kid) {
    kids.add(kid);
  }

  public String getName() {
    return this.name;
  }

  /**
   * Firstly check the name, if it is valid, change name
   *
   * @param name A String represent person name
   */
  private void setName(String name) throws IllegalArgumentException {
    if (!isValidName(name)) {
      throw new IllegalArgumentException("Name can not be empty");
    }
    this.name = name;
  }

  /**
   * Validates a name. Return false if the name is empty or does not match the ONE_WORD_NAME_REGEX
   *
   * @param name a string representing name to validate.
   * @return true if the name is valid, otherwise false
   */
  private boolean isValidName(String name) {
    return isNotNullAndNotEmpty(name);
  }

  public String getId() {
    return id;
  }

  public String getBirthDate() {
    if (this.birthDate == null) {
      return null;
    }
    return this.birthDate.format(DATE_TIME_FORMATTER);
  }

  private void setBirthDate(LocalDate birthDate) {
    if (LocalDate.now().isBefore(birthDate)) {
      return;
    }
    this.birthDate = birthDate;
  }

  public void setBirthDate(String birthDateStr) {
    try {
      setBirthDate(LocalDate.parse(birthDateStr));
    } catch (DateTimeException ex) {
      System.err.println(ex.getMessage());
    }
  }

  public boolean isOrderThan(Person person) {
    if (this.birthDate == null || person.birthDate == null) {
      return false;
    }
    return this.birthDate.isBefore(person.birthDate);
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public List<Person> getKids() {
    return kids;
  }

  public SexEnum getGender() {
    return gender;
  }

  public void setGender(SexEnum gender) {
    this.gender = gender;
  }

  public String getPhone() {
    if (this.phoneNumber == null) {
      return "";
    }
    return this.phoneNumber.getNumber();
  }

  public void setPhone(PhoneNumber phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public void setPhone(String phoneNumber) {
    String standardPhoneNumber = phoneNumber.trim();
    if (isValidPhoneNumber(standardPhoneNumber)) {
      this.phoneNumber = new PhoneNumber(standardPhoneNumber);
    }
  }

  public boolean isValidPhoneNumber(String phoneNumber) {
    char startNumber = '0';
    char endOfNumber = '9';
    for (char c : phoneNumber.toCharArray()) {
      if (c < startNumber || c > endOfNumber) {
        return false;
      }
    }
    return true;
  }



  public int getAge() {
    if (this.birthDate == null) {
      return -1;
    }
    LocalDate currentDate = LocalDate.now();
    return Period.between(birthDate, currentDate).getYears();
  }

  public void setAge(int age) {
    boolean isValidAge = age >= 0 && age <= 150;
    if (isValidAge) {
      int currenYear = LocalDate.now().getYear();
      int birthYear = currenYear - age;
      String fakeBirthdate = birthYear + "-01-01";
      setBirthDate(fakeBirthdate);
      return;
    }
    System.err.printf("age(%d) must be larger than 0 and small than 150%n", age);
  }



  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public boolean equals(Object personToEqual) {
    if (this == personToEqual) {
      return true;
    }
    if (personToEqual == null || getClass() != personToEqual.getClass()) {
      return false;
    }
    return id.equals(((Person) personToEqual).id);
  }

}
