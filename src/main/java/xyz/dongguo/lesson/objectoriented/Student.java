package xyz.dongguo.lesson.objectoriented;

import static xyz.dongguo.JsonHelper.printJson;

import java.util.Objects;

/**
 * @author dongguo
 */
public class Student extends Person {

  private boolean canSpeakFrench = false;

  private Student(String name) {
    super(name);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    Student student = (Student) o;
    return getCanSpeakFrench() == student.getCanSpeakFrench();
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), getCanSpeakFrench());
  }

  public static void main(String[] args) {
    Student student1 = null;
    Student student2 = null;
    Student student3 = null;
    try {
      student1 = new Student("Alice");
      student2 = new Student("Maria");
      student3 = new Student("");
    } catch (IllegalArgumentException ex) {
      System.err.println(ex.getMessage());
    }
    assert student1 != null;
    assert student2 != null;
    assert student3 != null;

    printJson(student1);
    printJson(student2);

    student1.setAge(100);
    student1.setBirthDate("2001-02-28");
    student1.setCanSpeakFrench(true);
    student1.setCanSpeakFrench(false);
    student1.setAge(100);

    student2.setAge(50);
    student2.setAge(155);
    student2.setBirthDate("2001-02-29");

    printJson(student1);
    printJson(student2);
  }

  public boolean getCanSpeakFrench() {
    return this.canSpeakFrench;
  }

  public void setCanSpeakFrench(boolean value) {
    if (this.canSpeakFrench) {
      return;
    }
    this.canSpeakFrench = value;
  }

  @Override
  public String toString() {
    return "{\"Student\":"
       + super.toString()
       + ",                         \"canSpeakFrench\":\"" + canSpeakFrench + "\""
       + "}";
  }
}
