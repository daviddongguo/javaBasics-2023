package xyz.dongguo.lesson.objectoriented;

public class Student {

  private String id;
  private String name;

  private Student() {
  }

  private Student(String id, String name) {
    setId(id);
    setName(name);
  }

  public static void main(String[] args) {
    Student student1 = Student.createStudent("jhna001", "abc");
    Student student2 = Student.createStudent("jhna002", "def");
    Student student3 = Student.tryCreateStudent("jhna003", "");
    //    Student student4 = new Student();

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
       '}';
  }
}
