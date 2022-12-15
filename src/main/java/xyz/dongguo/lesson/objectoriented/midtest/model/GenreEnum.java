package xyz.dongguo.lesson.objectoriented.midtest.model;

/**
 * @author dongguo
 */
public enum GenreEnum {
  /**
   * fantasy
   */
  FANTASY("fantasy"),
  /**
   * novel
   */
  NOVEL("novel");

  private final String name;

  private GenreEnum(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }
}
