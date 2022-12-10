package xyz.dongguo.lesson.objectoriented;

/**
 *
 * Has a method toJsonString() return json string
 * @author dongguo
 */
public interface Jsonable {

  /**
   * Create a string that represent a json object.
   * @return A string represent json object
   */
  String toJsonString();

}

