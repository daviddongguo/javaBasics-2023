package xyz.dongguo;

import com.google.gson.Gson;
import org.json.JSONObject;
import xyz.dongguo.lesson.objectoriented.Jsonable;

/**
 * symotion-overwin-f2)
 *
 * @author dongguo
 */
public class Json {

  public static final String JSON_PATTERN_FORMATTER = "{\"%S\" : %S}";

  private Json() {
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
