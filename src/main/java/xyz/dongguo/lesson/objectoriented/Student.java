package xyz.dongguo.lesson.objectoriented;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 * @author dongguo
 */
public class Student {

  public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  private String id;
  private String name;
  private boolean canSpeakFrench = false;
  private int age;
  private boolean isRealAge = false;
  private LocalDate birthDate;

  private Student() {
  }

  private Student(String id, String name) {
    setId(id);
    setName(name);
  }

  public static void main(String[] args) {
    Student student1 = Student.createStudent("student01", "abc");
    Student student2 = Student.createStudent("student02", "def");
    Student student3 = Student.tryCreateStudent("student03", "");

    System.out.println(student1);
    System.out.println(student2);
    System.out.println(student3);

    student1.setAge(100);
    student1.setBirthDate("2001-02-28");
    student1.setCanSpeakFrench(true);
    student1.setCanSpeakFrench(false);
    student1.setAge(100);

    student2.setAge(50);
    student2.setAge(155);
    student2.setBirthDate("2001-02-29");

    System.out.println(student1);
    System.out.println(student2);
    System.out.println(student3);
  }

  public static Student createStudent(String id, String name) {
    return new Student(id, name);
  }

  public static Student tryCreateStudent(String id, String name) {
    try {
      return new Student(id, name);
    } catch (Exception e) {
      return null;
    }
  }

  public String getBirthDate() {
    if (this.isRealAge) {
      return this.birthDate.format(DATE_TIME_FORMATTER);
    }
    return "";
  }

  public void setBirthDate(String birthdateStr) {
    setBirthDate(birthdateStr, true);
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

  public int getAge() {
    if (isRealAge) {
      return this.age;
    }
    return 0;
  }

  public void setAge(int age) {
    if (this.isRealAge) {
      return;
    }
    boolean isValidAge = age >= 0 && age <= 150;
    if (isValidAge) {
      int currenYear = LocalDate.now().getYear();
      int birthYear = currenYear - age;
      String fakeBirthdate = birthYear + "-01-01";
      setBirthDate(fakeBirthdate, false);
    }
  }

  private void setBirthDate(String birthDateStr, boolean isRealBirthdate) {
    try {
      this.birthDate = LocalDate.parse(birthDateStr);
      LocalDate currentDate = LocalDate.now();
      this.age = Period.between(birthDate, currentDate).getYears();
      this.isRealAge = isRealBirthdate;
    } catch (DateTimeException ex) {
      System.err.println(ex.getMessage());
    }
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    if (name != null && !name.trim().isEmpty()) {
      this.name = name;
      return;
    }
    throw new IllegalArgumentException("Invalid name");
  }

  @Override
  public String toString() {
    return "Student{" +
       "id='" + id + '\'' +
       ", name='" + name + '\'' +
       ", canSpeakFrench=" + canSpeakFrench +
       ", age=" + age +
       ", isRealAge=" + isRealAge +
       ", birthDate=" + birthDate +
       '}';
  }
}
