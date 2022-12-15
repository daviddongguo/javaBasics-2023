package xyz.dongguo.lesson.objectoriented.demo.dataCollection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import xyz.dongguo.lesson.objectoriented.demo.model.CollegeStudent;
import xyz.dongguo.lesson.objectoriented.demo.model.GraduateStudent;
import xyz.dongguo.lesson.objectoriented.demo.model.Person;
import xyz.dongguo.lesson.objectoriented.demo.model.Phone;
import xyz.dongguo.lesson.objectoriented.demo.model.Student;

public class DataCollection{
    // |  |  |  |  |  |  |  |  |  |  |
    public static List<Person> personArrayList = new ArrayList<>();

    public static void initializeModelArrayList(){
        Person person = new Person("John", "Abbott",
                new Phone(1,111,765,9870));
        Student student = new Student("Tieda","We",
                new Phone(1,222,353,4345),
                "tieda@gmail.com");
        GraduateStudent graduateStudent = new GraduateStudent(
                "Dennis",
                "Ritchie",
                new Phone(1,222,456,4234),
                "deniss@yahoo.com",
                "Developer");
        CollegeStudent collegeStudent = new CollegeStudent(
                "James",
                "Gosling",
                new Phone(1,333,567,8967),
                "@outlook.com");
        personArrayList.addAll(Arrays.asList(person, student, graduateStudent, collegeStudent));
    }

    public static void displayDataCollection(){
        System.out.println(personArrayList);
    }

    // ToDo: Methods in this class can be improved to handle duplicate names
    public static Person findPerson(String name){
        for (Person person : personArrayList) {
            if (person.getName().equals(name)){
                System.out.println(name + " is found. The index of " + name +
                        " in the ArrayList is: " +
                        DataCollection.personArrayList.indexOf(person));
                return person;
            }
        }
        return null;
    }

    public static boolean updatePersonPhoneNumber(String name,
                                                  int countryCode,
                                                  int areaCode,
                                                  int operatorCode,
                                                  int number){
        for (Person person : DataCollection.personArrayList) {
            if (person.getName().equals(name)){

                person.getPhone().setCountryCode(countryCode);
                person.getPhone().setAreaCode(areaCode);
                person.getPhone().setOperatorCode(operatorCode);
                person.getPhone().setNumber(number);

                System.out.println(name + " is found. The phone number is updated. " + person);
                return true;
            }
        }
        return false;
    }

    // ToDo: Use Iterator
    public static boolean removePerson(String name){
        for (Person person : personArrayList) {
            if(person.getName().equals(name)){
                personArrayList.remove(person);
                System.out.println(name + " is removed from ArrayList.");
            }
            return true;
        }
        return false;
    }
}