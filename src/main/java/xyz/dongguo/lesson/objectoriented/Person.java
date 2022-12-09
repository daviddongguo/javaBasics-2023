package xyz.dongguo.lesson.objectoriented;

import com.google.gson.Gson;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.json.JSONObject;

/**
 * @author dongguo
 */
public class Person implements Jsonable {

  public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  public static final String ALPHABET_NUMBER = "abcdefghijklmnopqrstuvwxyz0123456789";
  public static final int LENGTH_ID = 50;

  private final String id = generateRandomString(new Random(), LENGTH_ID);
  Address address;
  List<Person> kids = new ArrayList<>();
  private String name;
  private SexEnum gender;
  private Phone phone;
  private LocalDate birthDate;

  public Person(String name, Phone phone, Address address, SexEnum gender) {
    this(name, phone, address);
    this.setGender(gender);
  }

  public Person(String name, Phone phone, Address address) {
    this(name);
    this.phone = phone;
    this.address = address;
  }

  public Person(String name) {
    if (isValidName(name)) {
      this.setName(name);
    }
  }

  private boolean isValidName(String name) {
    return name != null && !name.isEmpty();
  }

  /**
   * for debugging
   */
  public static void main(String[] args) {
    List<Person> roster = Person.createRoster();
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

  private static void printAllJson(Person person) {
    Gson googleJson = new Gson();
    JSONObject json = new JSONObject(googleJson.toJson(person));
    System.out.println(json.toString(2));
  }

  private static void printPrettyJson(Jsonable obj) {
    JSONObject json = new JSONObject(obj.toJsonString());
    System.out.println(json.toString(2));
  }

  /**
   * debugging data includes three people
   *
   * @return three people
   */
  public static List<Person> createRoster() {
    List<Person> roster = new ArrayList<>();
    Address address = new Address("123", "King Street", "Quebec City");
    Phone phone1 = new Phone("5145131234");
    Phone phone2 = new Phone("5145131212");

    roster.add(new Person("Alice", phone1, address, SexEnum.FEMALE));
    roster.add(new Person("Bob", null, address, SexEnum.MALE));
    roster.add(new Person("Tom", phone2, address, SexEnum.MALE));

    return roster;
  }

  public static String generateRandomString(Random random, int length) {
    StringBuilder string = new StringBuilder();
    for (int i = 0; i < length; i++) {
      int randomIndex = random.nextInt(ALPHABET_NUMBER.length());
      string.append(ALPHABET_NUMBER.charAt(randomIndex));
    }
    return string.toString();
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

  private void setName(String name) {
    this.name = name;
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
    if (this.phone == null) {
      return "";
    }
    return this.phone.number;
  }

  public void setPhone(String phoneNumber) {
    String standardPhoneNumber = phoneNumber.trim();
    boolean isValidPhoneNumber = true;
    //TODO: valid phone number
    char firstNumber = standardPhoneNumber.charAt(0);
    char startNumber = '0';
    char endOfNumber = '9';
    if (firstNumber < startNumber || firstNumber > endOfNumber) {
      isValidPhoneNumber = false;
    }
    if (isValidPhoneNumber) {
      this.phone = new Phone(standardPhoneNumber);
    }
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
    if (phone != null) {
      phoneStr = subJsonBody(phone.toJsonString()) + " ,";
    }
    String jsonBody =
       String.format("\"name\" :  \"%s\" ,", name) + String.format("\"age\" :  %d ,", getAge()) + String.format("%s",
          addressStr) + String.format("%s", phoneStr)
          + String.format("%s", kidsStr);
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

}
