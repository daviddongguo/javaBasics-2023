package xyz.dongguo.lesson.objectoriented.school;

import com.google.gson.Gson;
import java.util.Collection;
import java.util.Random;
import org.json.JSONObject;

/**
 * @author dongguo
 */
public class JsonHelper {

  public static final String JSON_PATTERN_FORMATTER = "{\"%s\" : %s}";
  public static final String ALPHABET_NUMBER = "abcdefghijklmnopqrstuvwxyz0123456789";

  private JsonHelper() {
  }

  public static boolean isNotNullAndNotEmpty(String str) {
    return str != null && !str.isEmpty();
  }

  public static String generateRandomString(Random random, int length) {
    StringBuilder string = new StringBuilder();
    for (int i = 0; i < length; i++) {
      int randomIndex = random.nextInt(ALPHABET_NUMBER.length());
      string.append(ALPHABET_NUMBER.charAt(randomIndex));
    }
    return string.toString();
  }

  /**
   * Pretty print jason of an object.
   * <p> Using org.jso JSONObject to pretty-print the data.
   * <p>Using google.gson toJson
   * parse object to String.
   *
   * @param object any object, including array, list, and so on.
   */
  public static void printJson(Object object) {
    if (object instanceof Collection) {
      ((Collection<?>) object).forEach(JsonHelper::printJson);
      return;
    }

    String jsonString = (new Gson()).toJson(object);
    JSONObject jsonObject = new JSONObject(jsonString);
    System.out.println(jsonObject.toString(2));
  }

  public static void printJsonOnSingleLine(Object object) {
    String json = (new Gson()).toJson(object);
    System.out.println(json);
  }

  public static void printAll(Object... args) {
    for (Object o : args) {
      printJson(o);
    }
  }

}
