package xyz.dongguo.lesson.objectoriented.demo.model;

import java.util.Objects;

public class Student extends Person {
    private int studentId;
    private String email;
    private static int counter;

    public Student() {
        counter ++;
        this.studentId = counter;
    }

    public Student(String name, String family, Phone phone, String email) {
        super(name, family, phone);
        this.email = email;

        counter ++;
        this.studentId = counter;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + getName() + '\'' +
                ", family='" + getFamily() + '\'' +
                ", phone=" + getPhone() +
                ", studentId=" + studentId +
                ", email='" + email + '\'' +
                "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return studentId == student.studentId && Objects.equals(email, student.email);
    }
}