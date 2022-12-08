package xyz.dongguo.lesson.objectoriented;

import com.google.gson.Gson;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/**
 * @author dongguo
 */
public class Person implements Jsonable {

  public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  String name;
  Phone phone;
  Address address;
  List<Person> kids = new ArrayList<>();
  private LocalDate birthDate;

  public Person(String name, Phone phone, Address address) {
    this.name = name;
    this.phone = phone;
    this.address = address;
  }

  public static void main(String[] args) {
    Address address = new Address("123", "King Street", "Quebec City");
    Phone phone1 = new Phone("5145131234");
    Phone phone2 = new Phone("5145131212");

    Person personAlice = new Person("Alice", phone1, address);
    Person kidBob = new Person("Bob", null, address);
    Person kidTom = new Person("Tom", phone2, address);
    personAlice.setAge(-5);
    personAlice.setBirthDate("1980-07-21");
    kidBob.setAge(22);
    kidTom.setAge(1);
    kidTom.setBirthDate("2001-02-29");

    personAlice.addKid(kidBob);
    personAlice.addKid(kidTom);

    printAllJson(personAlice);
    printPrettyJson(personAlice);
    System.out.printf("%s birthdate is %s, age is %d%n", personAlice.name, personAlice.getBirthDate(),
       personAlice.getAge());

  }

  private static void printAllJson(Person person1) {
    Gson googleJson = new Gson();
    JSONObject json = new JSONObject(googleJson.toJson(person1));
    System.out.println(json.toString(2));

  }

  private static void printPrettyJson(Jsonable obj) {
    JSONObject json = new JSONObject(obj.toJsonString());
    System.out.println(json.toString(2));
  }

  public void addKid(Person kid) {
    kids.add(kid);
  }

  public String getBirthDate() {
    return this.birthDate.format(DATE_TIME_FORMATTER);
  }

  private void setBirthDate(String birthDateStr) {
    try {
      this.birthDate = LocalDate.parse(birthDateStr);
    } catch (DateTimeException ex) {
      System.err.println(ex.getMessage());
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

  public int getAge() {
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
}
