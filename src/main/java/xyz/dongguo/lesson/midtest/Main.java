package xyz.dongguo.lesson.midtest;

/**
 * Call App class
 *
 * @author dongguo
 */
public class Main {

  public static void main(String[] args) {
    System.out.printf("%nPrint all books in the DataCollection ArrayList%n");
    App.createModelObjects();

    System.out.printf("%nPrint the books written by J.R.R Tolkien%n");
    App.findDemo();

    System.out.printf("%nTry to add \"Le Pettit Prince\" again");
    System.out.printf("%nThen print all books in the DataCollection ArrayList%n");
    App.preventAddingTwoBooksWithSameNameDemo();
  }
}