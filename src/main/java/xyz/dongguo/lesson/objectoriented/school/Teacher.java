package xyz.dongguo.lesson.objectoriented.school;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author map
 */
public class Teacher extends Person {

  private final List<Course> courseList = new ArrayList<>();

  public Teacher(String name) throws IllegalArgumentException {
    super(name);
  }

  public static void main(String[] args) {
    Teacher teacherAlice = new Teacher("Alice");
    List<Course> courses = new ArrayList<>(List.of(new Course("CS Basic 1"), new Course("CS Basic 2")));

    System.out.println("-----Init Course List------");
    teacherAlice.setCourseList(courses);
    Course courseJava2 = new Course("Java objector oriented 2");
    teacherAlice.addCourse(new Course("Java basic 1"));
    teacherAlice.addCourse(courseJava2);
    printList(teacherAlice.courseList);

    System.out.println("-----Find Java Related Courses------");
    printList(teacherAlice.findCoursesByName("java"));

    System.out.println("-----Update Course List------");
    courseJava2.setName("java basic 2");
    teacherAlice.updateCourse(courseJava2);
    printList(teacherAlice.courseList);

    System.out.println("-----Remove Course From Course List------");
    teacherAlice.removeCourse(courseJava2);
    printList(teacherAlice.courseList);
  }

  private static void printList(List<Course> list) {
    for (Course item : list) {
      System.out.println(item.getName());
    }
  }

  public void addCourse(Course course) {
    courseList.add(course);
  }

  /**
   * Remove the course whose id equals the id of the course in the course list.
   *
   * @param course The course will be removed.
   */
  public void removeCourse(Course course) {
    Course courseToRemove = null;
    for (Course currentCourse : courseList) {
      if (course.getId().equals(currentCourse.getId())) {
        courseToRemove = currentCourse;
      }
    }
    courseList.remove(courseToRemove);
  }

  /**
   * Update the course whose id equals the id of course in the course list.
   *
   * @param course The course will be updated.
   */
  public void updateCourse(Course course) {
    for (Course currentCourse : courseList) {
      if (course.getId().equals(currentCourse.getId())) {
        currentCourse.setName(course.getName());
      }
    }
  }

  public List<Course> findCoursesByName(String queryStr) {
    List<Course> courses = new ArrayList<>(7);
    for (Course currentCourse : courseList) {
      if (currentCourse.getName().toLowerCase().contains(queryStr.toLowerCase())) {
        courses.add(currentCourse);
      }
    }
    return courses;
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
    Teacher teacher = (Teacher) o;
    return Objects.equals(getCourseList(), teacher.getCourseList());
  }

  @Override
  public String toString() {
    return "{\"Teacher\":" + super.toString() + ",                         \"courses\":" + courseList + "}";
  }

  public List<Course> getCourseList() {
    return courseList;
  }

  public void setCourseList(List<Course> courseList) {
    this.courseList.addAll(courseList);
  }
}
