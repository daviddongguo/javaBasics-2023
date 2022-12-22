package xyz.dongguo.lesson.midtest.model;

import java.util.Objects;

/**
 * @author dongg
 */
public class Author {

  private static int counter;
  private final int id;
  private String firstName;
  private String lastName;

  public Author(String firstName, String lastName) {
    counter++;
    this.id = counter;
    setFirstName(firstName);
    setLastName(lastName);
  }

  public int getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Author author = (Author) o;
    return firstName.equals(author.firstName) && lastName.equals(author.lastName);
  }

  @Override
  public String toString() {
    return "Author{" +
       "firstName='" + firstName + '\'' +
       ", lastName='" + lastName + '\'' +
       '}';
  }
}
