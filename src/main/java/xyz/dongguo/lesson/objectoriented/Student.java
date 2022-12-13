package xyz.dongguo.lesson.objectoriented;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author dongguo
 */
public class Student extends Person {

  private List<StudentCourse> courseList = new ArrayList<>(7);

  public Student(String name) {
    super(name);
  }

  public static void main(String[] args) {
    Student studentAlice = new Student("Alice");

    studentAlice.setCourseList(new ArrayList<>(
       List.of(
          new StudentCourse(studentAlice.getId(), Course.createCourseList().get(0).getId(), 90),
          new StudentCourse(studentAlice.getId(), Course.createCourseList().get(1).getId(), 80),
          new StudentCourse(studentAlice.getId(), Course.createCourseList().get(2).getId(), 90),
          new StudentCourse(studentAlice.getId(), Course.createCourseList().get(3).getId(), 80)
       )));

    studentAlice.courseList.forEach(System.out::println);
    System.out.println(studentAlice.getAverageGrade());
  }

  public int getAverageGrade() {
    if (this.courseList.isEmpty()) {
      return 0;
    }
    int size = this.courseList.size();
    float totalGrade = this.courseList.get(0).getGrade();
    int numberOfCourse = 1;
    for (int i = 1; i < size; i++) {
      totalGrade += this.courseList.get(i).getGrade();
      numberOfCourse++;
    }

    return (int) (totalGrade / numberOfCourse);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), getCourseList());
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
    return Objects.equals(getCourseList(), student.getCourseList());
  }

  @Override
  public String toString() {
    return "{\"Student\":"
       + super.toString()
       + ",                         \"courseList\":" + courseList
       + "}";
  }

  public List<StudentCourse> getCourseList() {
    return courseList;
  }

  public void setCourseList(List<StudentCourse> courseList) {
    this.courseList = courseList;
  }
}
