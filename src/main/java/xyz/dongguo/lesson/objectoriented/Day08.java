package xyz.dongguo.lesson.objectoriented;

/**
 * @author dongg
 */
public class Day08 {

  public static void main(String[] args) {
    Student student1 = Student.createStudent("jhna001", "abc");
    Student student2 = Student.createStudent("jhna002", "def");
    Student student3 = Student.tryCreateStudent("jhna003", "");
    //    Student student4 = new Student();

    System.out.println(student1);
    System.out.println(student2);
    System.out.println(student3);

  }

}
