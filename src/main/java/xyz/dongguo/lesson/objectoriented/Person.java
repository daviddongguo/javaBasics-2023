package xyz.dongguo.lesson.objectoriented;



import static xyz.dongguo.Json.generateRandomString;
import static xyz.dongguo.Json.isNotNullAndNotEmpty;
import static xyz.dongguo.Json.printAllJson;
import static xyz.dongguo.Json.printPrettyJson;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * The Person class represents a person.
 *
 * @author dongguo
 * @version 1.2
 */
public class Person implements Jsonable {

  public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  /**
   * The start of the string matches any uppercase letter,
   * no more than 20 characters
   */
  public static final String ONE_WORD_NAME_REGEX = "^[a-z]{1,19}$";
  public static final int LENGTH_ID = 50;

  private final String id = generateRandomString(new Random(), LENGTH_ID);
  private final List<Person> kids = new ArrayList<>();
  private Address address;
  private String name;
  private SexEnum gender;
  private PhoneNumber phoneNumber;
  private LocalDate birthDate;

  public Person(String name, PhoneNumber phoneNumber, Address address, SexEnum gender) throws IllegalArgumentException{
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
    List<Person> roster = Person.createPeopleList();
    Person personAlice = roster.get(0);
    Person kidBob = roster.get(1);
    Person kidTom = roster.get(2);

    personAlice.setAge(-5);
    personAlice.setBirthDate("1980-07-21");
    kidBob.setAge(22);
    kidTom.setAge(1);
    kidTom.setBirthDate("2001-02-29");

    personAlice.addKid(kidBob);
    personAlice.addKid(kidTom);

    printAllJson(personAlice);
    printPrettyJson(personAlice);
    System.out.printf("%s birthdate is %s, age is %d%n", personAlice.getName(), personAlice.getBirthDate(),
       personAlice.getAge());
    System.out.printf("%s %s order than %s%n", kidBob.getName(), kidBob.isOrderThan(kidTom) ? "is" : "is not",
       kidTom.getName());
    System.out.printf("%s %s order than %s%n", kidTom.getName(), kidTom.isOrderThan(kidBob) ? "is" : "is not",
       kidBob.getName());

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



  public String getId() {
    return id;
  }

  public void addKid(Person kid) {
    kids.add(kid);
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
   * Validates a name.
   * Return false if the name is empty
   * or does not match the ONE_WORD_NAME_REGEX
   *
   * @param name a string representing name to validate.
   * @return true if the name is valid, otherwise false
   */
  private boolean isValidName(String name) {
    return isNotNullAndNotEmpty(name) ;
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

  @Override
  public String toJsonString() {
    String kidsStr = "";
    if (!this.kids.isEmpty()) {
      kidsStr = String.format("\"kids\" :  %s", toJsonStringFromList(this.kids));
    }
    String addressStr = "";
    if (address != null) {
      addressStr = subJsonBody(address.toJsonString()) + " , ";
    }
    String phoneStr = "";
    if (phoneNumber != null) {
      phoneStr = subJsonBody(phoneNumber.toJsonString()) + " ,";
    }
    String jsonBody =
       String.format("\"name\" :  \"%s\" ,", name) + String.format("\"age\" :  %d ,", getAge()) + String.format("%s",
          addressStr) + String.format("%s", phoneStr) + String.format("%s", kidsStr);
    return String.format("{%s}", jsonBody);
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

  private String toJsonStringFromList(List<Person> list) {
    if (list.isEmpty()) {
      return "";
    }

    StringBuilder stringBuilder = new StringBuilder("[ ");
    for (Jsonable item : list) {
      stringBuilder.append(item.toJsonString()).append(" ,");
    }
    stringBuilder.append(" ]");
    return stringBuilder.toString();
  }

  private String subJsonBody(String jsonStr) {
    if (jsonStr == null) {
      return "";
    }
    int size = jsonStr.length();
    int miniSizeOfJson = 2;
    if (size <= miniSizeOfJson) {
      return "";
    }
    return jsonStr.substring(1, size - 1);
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
