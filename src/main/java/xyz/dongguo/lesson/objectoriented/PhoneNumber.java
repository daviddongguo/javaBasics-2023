package xyz.dongguo.lesson.objectoriented;

import static xyz.dongguo.Json.JSON_PATTERN_FORMATTER;
import static xyz.dongguo.Json.printPrettyJson;

import java.util.ArrayList;
import java.util.List;

/**
 * The PhoneNumber class represents a phone number. It stores the phone number and provides methods for getting and
 * setting the number, checking if the number is valid, and formatting the number.
 *
 * @author dongguo
 * @version 1.2
 */
public class PhoneNumber implements Jsonable {

  public static final String AMERICAN_PHONE_NUMBER_FORMATTER = "+1 (%s) %s-%s";

  private String number;

  public PhoneNumber(String number) {
    setNumber(number);
  }

  public static void main(String[] args) {
    List<PhoneNumber> list = createPhoneList();
    String startWith514 = "514";
    for (PhoneNumber phoneNumber : list) {
      if (phoneNumber.getNumber().startsWith(startWith514)) {
        printPrettyJson(phoneNumber);
      }
    }
  }

  public static List<PhoneNumber> createPhoneList() {
    return new ArrayList<>(
       List.of(new PhoneNumber("5148130001"), new PhoneNumber("5148130022"), new PhoneNumber("5148130033"),
          new PhoneNumber("4388140043"), new PhoneNumber("4388130055"), new PhoneNumber("5148867788")));
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    try {
      Long.parseLong(number);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Phone number must be numeric types.");
    }

    boolean isValidPhoneNumber =  number.matches("^\\d{10}$");
    if (!isValidPhoneNumber) {
      throw new IllegalArgumentException("Phone number must have 10 digits.");
    }
    this.number = number;
  }

  @Override
  public String toString() {
    return toJsonString();
  }

  @Override
  public String toJsonString() {
    return String.format(JSON_PATTERN_FORMATTER, "phoneNumber", getFormattedNumber());
  }

  /**
   * Returns the phone number stored in the object in a formatted string. The formatted string has a space after the
   * third and seventh characters.
   *
   * @return the formatted phone number
   */
  public String getFormattedNumber() {
    return String.format(AMERICAN_PHONE_NUMBER_FORMATTER, number.substring(0, 3), number.substring(3, 6),
       number.substring(6));
  }
}
