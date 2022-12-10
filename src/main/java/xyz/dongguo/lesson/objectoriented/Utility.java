package xyz.dongguo.lesson.objectoriented;

import com.google.gson.Gson;
import org.json.JSONObject;

/**
 * @author dongguo
 */
public class Utility {

  private Utility() {
  }

  /**
   * Print json string of an object
   *
   * @param object any object
   */
  public static void printAllJson(Object object) {
    Gson googleJson = new Gson();
    JSONObject json = new JSONObject(googleJson.toJson(object));
    System.out.println(json.toString(2));
  }

  /**
   * Print json string in a better style
   *
   * @param obj a object implements Jsonable interface
   */
  public static void printPrettyJson(Jsonable obj) {
    JSONObject json = new JSONObject(obj.toJsonString());
    System.out.println(json.toString(2));
  }

}
