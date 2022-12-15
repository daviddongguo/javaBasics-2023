package xyz.dongguo.lesson.objectoriented.demo;

import xyz.dongguo.lesson.objectoriented.demo.dataCollection.DataCollection;

public class App {
    public App() {
        DataCollection.initializeModelArrayList();
        DataCollection.displayDataCollection();
        DataCollection.findPerson("Dennis");
        DataCollection.updatePersonPhoneNumber("James",
                123,
                456,
                789,
                1011);
        DataCollection.removePerson("John");
    }
}