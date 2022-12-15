package xyz.dongguo.lesson.objectoriented.school;

import static xyz.dongguo.JsonHelper.JSON_PATTERN_FORMATTER;
import static xyz.dongguo.JsonHelper.isNotNullAndNotEmpty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The PhoneNumber class represents a phone number. It stores the phone number and provides methods for getting and
 * setting the number, checking if the number is valid, and formatting the number.
 *
 * @author dongguo
 * @version 1.2
 */
public class PhoneNumber {

  public static final String AMERICAN_PHONE_NUMBER_FORMATTER = "+1 (%s) %s-%s";
  public static final int PHONE_NUMBER_LENGTH = 10;

  private String number;

  public PhoneNumber(String number) {
    setNumber(number);
  }

  public static void main(String[] args) {
    List<PhoneNumber> list = createPhoneList();
    String startWith514 = "514";
    for (PhoneNumber phoneNumber : list) {
      if (phoneNumber.getNumber().startsWith(startWith514)) {
        System.out.println(phoneNumber);
      }
    }

    PhoneNumber phone = PhoneNumber.fromJson(list.get(0).toString());
    System.out.println(phone);
  }

  /**
   * create a phoneNumber instance by using static factory method
   *
   * @param json a json string representing a phoneNumber object
   * @return a phoneNumber object
   */
  public static PhoneNumber fromJson(String json) {
    char[] charArray = json.toCharArray();
    StringBuilder phoneNumberStr = new StringBuilder();
    for (char c : charArray) {
      if (Character.isDigit(c)) {
        phoneNumberStr.append(c);
      }
    }

    return new PhoneNumber(phoneNumberStr.substring(Math.max(phoneNumberStr.length() - PHONE_NUMBER_LENGTH, 0)));
  }

  public static List<PhoneNumber> createPhoneList() {
    return new ArrayList<>(
       List.of(new PhoneNumber("5148130001"), new PhoneNumber("5148130022"), new PhoneNumber("5148130033"),
          new PhoneNumber("4388140043"), new PhoneNumber("4388130055"), new PhoneNumber("5148867788")));
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String phoneNumber) {
    try {
      Long.parseLong(phoneNumber);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Phone number must be numeric types.");
    }

    if (!isValidPhoneNumber(phoneNumber)) {
      throw new IllegalArgumentException("Phone number must have 10 digits.");
    }
    this.number = phoneNumber;
  }

  public boolean isValidPhoneNumber(String phoneNumber) {
    if (isNotNullAndNotEmpty(phoneNumber)) {
      return phoneNumber.matches("^\\d{10}$");
    }
    return false;
  }

  /**
   * @return A json string like this  {"phoneNumber" : +1 (513) 813-1234}
   */
  @Override
  public String toString() {
    return toJsonString();
  }

  public String toJsonString() {
    return String.format(JSON_PATTERN_FORMATTER, "phoneNumber", "\"" + getFormattedNumber() + "\"");
  }

  /**
   * Returns the formatted string representation of this phone number. The format is "XXX-YYY-ZZZ". Each of the capital
   * letters represents a single decimal digit.
   *
   * @return the formatted phone number
   */
  public String getFormattedNumber() {
    return String.format(AMERICAN_PHONE_NUMBER_FORMATTER, number.substring(0, 3), number.substring(3, 6),
       number.substring(6));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PhoneNumber that = (PhoneNumber) o;
    return number.equals(that.number);
  }

  @Override
  public int hashCode() {
    return Objects.hash(number);
  }
}
