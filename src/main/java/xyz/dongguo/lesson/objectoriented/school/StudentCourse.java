package xyz.dongguo.lesson.objectoriented.school;

/**
 * @author dongguo
 */
public class StudentCourse {

  private String studentId;
  private String courseId;
  private int grade;

  public StudentCourse(String studentId, String courseId, int grade) {
    this.studentId = studentId;
    this.courseId = courseId;
    this.grade = grade;
  }

  public String getStudentId() {
    return studentId;
  }

  public void setStudentId(String studentId) {
    this.studentId = studentId;
  }

  public String getCourseId() {
    return courseId;
  }

  public void setCourseId(String courseId) {
    this.courseId = courseId;
  }

  public int getGrade() {
    return grade;
  }

  public void setGrade(int grade) {
    this.grade = grade;
  }

  @Override
  public String toString() {
    return "{\"StudentCourse\":{"
       + "                        \"StudentId\":\"" + studentId + "\""
       + ",                         \"CourseId\":\"" + courseId + "\""
       + ",                         \"grade\":\"" + grade + "\""
       + "}}";
  }
}