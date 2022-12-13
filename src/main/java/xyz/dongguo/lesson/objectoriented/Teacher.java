package xyz.dongguo.lesson.objectoriented;

import static xyz.dongguo.JsonHelper.printJson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author map
 */
public class Teacher extends Person {

  private List<Course> courses = new ArrayList<>();

  public Teacher(String name) throws IllegalArgumentException {
    super(name);
  }

  public static void main(String[] args) {
    Teacher teacherAlice = new Teacher("Alice");
    Course courseJava2 = new Course("Java objector oriented 2");
    teacherAlice.addCourse(new Course("Java basic 1"));
    teacherAlice.addCourse(courseJava2);
    printJson(teacherAlice);

    courseJava2.setName("java basic 2");
    teacherAlice.updateCourse(courseJava2);
    printJson(teacherAlice);

    teacherAlice.removeCourse(courseJava2);
    printJson(teacherAlice);
  }

  public void addCourse(Course course) {
    courses.add(course);
  }

  public void removeCourse(Course course) {
    Course courseToRemove = null;
    for (Course currentCourse : courses) {
      if (course.getId().equals(currentCourse.getId())) {
        courseToRemove = currentCourse;
      }
    }
    courses.remove(courseToRemove);
  }

  public void updateCourse(Course course) {
    for (Course currentCourse : courses) {
      if (course.getId().equals(currentCourse.getId())) {
        currentCourse.setName(course.getName());
      }
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), getCourses());
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
    Teacher teacher = (Teacher) o;
    return Objects.equals(getCourses(), teacher.getCourses());
  }

  @Override
  public String toString() {
    return "{\"Teacher\":"
       + super.toString()
       + ",                         \"courses\":" + courses
       + "}";
  }

  public List<Course> getCourses() {
    return courses;
  }

  public void setCourses(List<Course> courses) {
    this.courses = courses;
  }
}
