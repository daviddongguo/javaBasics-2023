package xyz.dongguo.lesson.objectoriented.school;

import static xyz.dongguo.lesson.objectoriented.school.JsonHelper.generateRandomString;
import static xyz.dongguo.lesson.objectoriented.school.Person.LENGTH_ID;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author dongguo
 */
public class Course {

  private final String id = generateRandomString(new Random(), LENGTH_ID);
  private String name;

  public Course(String name) {
    this.name = name;
  }

  public static List<Course> createCourseList() {
    return new ArrayList<>(List.of(
       new Course("CS Basic 1"),
       new Course("CS Basic 2"),
       new Course("Java Basic 1"),
       new Course("Java Basic 2")
    ));
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getId() {
    return id;
  }

}
