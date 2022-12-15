package xyz.dongguo.lesson.objectoriented.demo.model;

import xyz.dongguo.lesson.objectoriented.demo.validation.MyValidation;

public class Person {
    private String name;
    private String family;
    private Phone phone;

    public Person() {
    }

    public Person(String name, String family, Phone phone) {
        this.name = name;
        this.family = family;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(MyValidation.nameValidation(name)){
            this.name = name;
        }else {
            System.out.println("Bad name");
            this.name = "N/A";
        }
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", family='" + family + '\'' +
                ", phone=" + phone +
                "}\n";
    }
}