package xyz.dongguo.lesson.midtest;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import xyz.dongguo.lesson.midtest.datacollection.DataCollection;
import xyz.dongguo.lesson.midtest.model.Author;
import xyz.dongguo.lesson.midtest.model.Book;
import xyz.dongguo.lesson.midtest.model.GenreEnum;
import xyz.dongguo.lesson.midtest.model.LanguageEnum;

/**
 * Has three methods for debugging
 *
 * @author dongguo
 */
public class App {

  private static final String J_R_R_TOLKIEN_FIRST_NAME = "J.R.R.";
  private static final String J_R_R_TOLKIEN_LAST_NAME = "Tolkien";
  private static final String LE_PETTIT_PRINCE = "Le Pettit Prince";

  private App() {
  }

  public static void createModelObjects() {
    Author authorJrrtolkien = new Author(J_R_R_TOLKIEN_FIRST_NAME, J_R_R_TOLKIEN_LAST_NAME);
    Author authorAntonieSaintExupery = new Author("Antonie de", "Saint-Exupery");
    Author authorJkrowling = new Author("J.K", "Rowling");
    Author authorJohnAbbott = new Author("John", " Abbott");

    List<Book> bookList = new ArrayList<>(List.of(
       new Book("The Lord Of the Rings", new ArrayList<>(List.of(authorJrrtolkien)), LanguageEnum.ENGLISH,
          parseYear("1954"), 150000000, GenreEnum.FANTASY),
       new Book(LE_PETTIT_PRINCE, new ArrayList<>(List.of(authorAntonieSaintExupery)), LanguageEnum.FRENCH,
          parseYear("1943"), 140000000, GenreEnum.NOVEL),
       new Book("Harry Potter and Philosopher’s Stone", new ArrayList<>(List.of(authorJkrowling)), LanguageEnum.ENGLISH,
          parseYear("1997"), 120000000, GenreEnum.FANTASY),
       new Book("The Hobbit", new ArrayList<>(List.of(authorJrrtolkien, authorJohnAbbott)), LanguageEnum.ENGLISH,
          parseYear("1937"), 103000000, GenreEnum.FANTASY)
    ));

    DataCollection.addAll(bookList);
    printBookArrayList(DataCollection.bookArrayList);
  }

  public static void findDemo() {
    List<Book> booksToFind = DataCollection.find(new Author(J_R_R_TOLKIEN_FIRST_NAME, J_R_R_TOLKIEN_LAST_NAME));
    printBookArrayList(booksToFind);
  }

  public static void preventAddingTwoBooksWithSameNameDemo() {
    Book newBook = new Book(LE_PETTIT_PRINCE);
    DataCollection.add(newBook);
    printBookArrayList(DataCollection.bookArrayList);
  }

  private static void printBookArrayList(List<Book> bookList){
    System.out.printf("There are %d books as below: %n", bookList.size());
    for (Book book : bookList) {
      System.out.printf("\t%s%n", book);
    }
  }

  private static Date parseYear(String yearString) {
    Date date = null;
    try {
      date = Book.ONLY_YEAR_DATE_FORMATTER.parse(yearString);
    } catch (ParseException e) {
      System.err.println(e.getMessage());
    }
    return date;
  }

}
