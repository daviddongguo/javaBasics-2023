package xyz.dongguo.lesson.objectoriented.midtest.model;

/**
 * @author dongg
 */
public enum LanguageEnum {
  /**
   * English
   */
  ENGLISH("English"),
  /**
   * French
   */
  FRENCH("French"),;

  private final String name;

  private LanguageEnum(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }
}
