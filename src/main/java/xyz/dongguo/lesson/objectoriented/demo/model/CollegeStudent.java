package xyz.dongguo.lesson.objectoriented.demo.model;

import java.util.ArrayList;
import xyz.dongguo.lesson.objectoriented.demo.service.Employable;

public class CollegeStudent extends Student {
    private ArrayList<String> projectsArrayList;
    private Employable employable;

    public CollegeStudent() {
    }

    public CollegeStudent(String name, String family, Phone phone, String email) {
        super(name, family, phone, email);
    }

    public ArrayList<String> getProjectsArrayList() {
        return projectsArrayList;
    }

    public void setProjectsArrayList(String project) {
        this.projectsArrayList.add(project);
    }

    public Employable getEmployable() {
        return employable;
    }

    public void setEmployable(Employable employable) {
        this.employable = employable;
    }

    @Override
    public String toString() {
        return "CollegeStudent{" +
                "name='" + getName() + '\'' +
                ", family='" + getFamily() + '\'' +
                ", phone=" + getPhone() +
                ", projectsArrayList=" + projectsArrayList +
                ", employable=" + employable +
                "}\n";
    }
}