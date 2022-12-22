package xyz.dongguo.lesson.objectoriented.midtest.datacollection;

import java.util.ArrayList;
import java.util.List;
import xyz.dongguo.lesson.objectoriented.midtest.model.Author;
import xyz.dongguo.lesson.objectoriented.midtest.model.Book;

/**
 * @author dongg
 */
public class DataCollection {

  public static final List<Book> bookArrayList = new ArrayList<>();

  private DataCollection() {
  }

  /**
   * Add a new book into the book array list
   * @param bookToAdd a book object
   */
  public static void add(Book bookToAdd) {
    if (findByName(bookToAdd) != null) {
      return;
    }
    bookArrayList.add(bookToAdd);
  }

  /**
   * Find a book by Author if we have more than one book from one Author you have to show a list of books
   * @param authorToQuery an Author object
   * @return an array list of books
   */
  public static List<Book> find(Author authorToQuery) {
    return findByAuthor(authorToQuery);
  }


  private static Book findByName(Book bookToQuery) {
    for (Book currentBook : bookArrayList) {
      if (currentBook.equals(bookToQuery)) {
        return currentBook;
      }
    }
    return null;
  }

  public static void addAll(List<Book> books) {
    for (Book currentBook : books) {
      add(currentBook);
    }
  }

  public static List<Book> findByAuthor(Author authorToQuery) {
    ArrayList<Book> booksToFind = new ArrayList<>(7);
    for (Book currentBook : bookArrayList) {
      for (Author currentAuthor : currentBook.getAuthorList()) {
        if (currentAuthor.equals(authorToQuery)) {
          booksToFind.add(currentBook);
        }
      }
    }

    return booksToFind;
  }
}
