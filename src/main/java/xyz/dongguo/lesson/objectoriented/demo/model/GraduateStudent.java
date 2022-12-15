package xyz.dongguo.lesson.objectoriented.demo.model;

public class GraduateStudent extends Student {
    String job;

    public GraduateStudent() {
    }

    public GraduateStudent(String name, String family, Phone phone, String email, String job) {
        super(name, family, phone, email);
        this.job = job;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "GraduateStudent{" +
                "name='" + getName() + '\'' +
                ", family='" + getFamily() + '\'' +
                ", phone=" + getPhone() +
                ", job='" + job + '\'' +
                "}\n";
    }
}
