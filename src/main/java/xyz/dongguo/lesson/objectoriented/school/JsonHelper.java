package xyz.dongguo.lesson.objectoriented.school;

import com.google.gson.Gson;
import java.io.PrintStream;
import java.util.Collection;
import java.util.Random;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author dongguo
 */
public class JsonHelper {

  public static final String JSON_PATTERN_FORMATTER = "{\"%s\" : %s}";
  public static final String ALPHABET_NUMBER = "abcdefghijklmnopqrstuvwxyz0123456789";

  private JsonHelper() {
  }

  public static boolean isNullOrEmpty(@Nullable String str) {
    return str == null || str.trim().isEmpty();
  }

  public static String generateRandomString(Random random, int length) {
    StringBuilder string = new StringBuilder();
    for (int i = 0; i < length; i++) {
      int randomIndex = random.nextInt(ALPHABET_NUMBER.length());
      string.append(ALPHABET_NUMBER.charAt(randomIndex));
    }
    return string.toString();
  }

  public static void printJsonOnSingleLine(Object object, PrintStream out) {
    out.println(toJsonStringOnSingleLine(object));
  }

  public static String toJsonStringOnSingleLine(Object object) {
    return (new Gson()).toJson(object);
  }

  public static void printAll(PrintStream out, Object... args) {
    out.println(allToJsonString(args));
  }

  public static String allToJsonString(Object... args) {
    StringBuilder str = new StringBuilder();
    for (Object o : args) {
      str.append(toPrettyJsonString(o)).append("\n");
    }
    return str.toString();
  }

  /**
   * Pretty print jason of an object.
   * <p> Using org.jso JSONObject to pretty-print the data.
   * <p>Using google.gson toJson
   * parse object to String.
   *
   * @param object any object, including array, list, and so on.
   */
  public static void printJson(Object object, PrintStream out) {
    out.print(toPrettyJsonString(object));
  }

  public static String toPrettyJsonString(Object object) {
    StringBuilder str = new StringBuilder();
    if (object instanceof Collection) {
      ((Collection<?>) object).forEach(o -> str.append(toPrettyJsonString(o)).append(String.format("%n")));
      return str.toString();
    }

    String jsonString = (new Gson()).toJson(object);
    JSONObject jsonObject = new JSONObject(jsonString);
    str.append(jsonObject.toString(2));
    return str.toString();
  }

  public static JSONArray JSONObjectToArray(String string) {
    JSONObject jsonArrayObject = new JSONObject(string);
    JSONArray keyStrings = listNumberArray(jsonArrayObject.length());
    return jsonArrayObject.toJSONArray(keyStrings);
  }

  private static JSONArray listNumberArray(int max) {
    JSONArray res = new JSONArray();
    for (int i = 0; i < max; i++) {
      //The value of the labels must be a String in order to make it work
      res.put(String.valueOf(i));
    }
    return res;
  }

}
