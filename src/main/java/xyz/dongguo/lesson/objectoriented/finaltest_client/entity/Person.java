package xyz.dongguo.lesson.objectoriented.finaltest_client.entity;

import java.util.Objects;

public class Person implements Comparable<Person> {

  private final int SIN;
  private String firstName;
  private String lastName;

  public Person(int SIN, String firstName, String lastName) {
    this.SIN = SIN;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public int getSIN() {
    return SIN;
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
  public String toString() {
    return "Person{" +
       "SIN=" + SIN +
       ", firstName='" + firstName + '\'' +
       ", LastName='" + lastName + '\'' +
       '}';
  }

  @Override
  public int compareTo(Person personToCompare) {
    return this.lastName.compareTo(personToCompare.lastName);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Person person = (Person) o;
    return getFirstName().equals(person.getFirstName()) && getLastName().equals(person.getLastName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getFirstName(), getLastName());
  }
}
