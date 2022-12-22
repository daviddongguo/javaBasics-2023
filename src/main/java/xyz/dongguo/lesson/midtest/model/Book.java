package xyz.dongguo.lesson.midtest.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author dongguo
 */
public class Book implements Comparable<Book>{
  public static final SimpleDateFormat ONLY_YEAR_DATE_FORMATTER = new SimpleDateFormat("yyyy");

  private final String name;
  private List<Author> authorList;
  private LanguageEnum language;
  private Date firstPublished;
  private int approximateSales;
  private GenreEnum genre;

  public Book(String name) {
    this.name = name;
  }

  public Book(String name, List<Author> authorList, LanguageEnum language, Date firstPublished, int approximateSales,
     GenreEnum genre) {
    this(name);
    setAuthorList(authorList);
    setLanguage(language);
    setFirstPublished(firstPublished);
    setApproximateSales(approximateSales);
    setGenre(genre);
  }

  @Override
  public String toString() {
    return "Book{" +
       "name='" + name + '\'' +
       ", authorList=" + authorList +
       ", language=" + language +
       ", firstPublished=" + ONLY_YEAR_DATE_FORMATTER.format(firstPublished) +
       ", approximateSales(million)=" + approximateSales / 1000000 +
       ", genre=" + genre +
       '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Book book = (Book) o;
    return Objects.equals(getName(), book.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName());
  }

  public String getName() {
    return name;
  }

  public List<Author> getAuthorList() {
    return authorList;
  }

  public void setAuthorList(List<Author> authorList) {
    this.authorList = authorList;
  }

  public LanguageEnum getLanguage() {
    return language;
  }

  public void setLanguage(LanguageEnum language) {
    this.language = language;
  }

  public Date getFirstPublished() {
    return firstPublished;
  }

  public void setFirstPublished(Date firstPublished) {
    this.firstPublished = firstPublished;
  }

  public int getApproximateSales() {
    return approximateSales;
  }

  public void setApproximateSales(int approximateSales) {
    this.approximateSales = approximateSales;
  }

  public GenreEnum getGenre() {
    return genre;
  }

  public void setGenre(GenreEnum genre) {
    this.genre = genre;
  }

  @Override
  public int compareTo(Book o) {
    return this.name.compareTo(o.name);
  }
}
