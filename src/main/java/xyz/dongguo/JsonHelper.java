package xyz.dongguo;

import com.google.gson.Gson;
import java.util.Random;
import org.json.JSONObject;

/**
 * @author dongguo
 */
public class JsonHelper {

  public static final String JSON_PATTERN_FORMATTER = "{\"%S\" : %S}";
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
   * @param obj a object
   */
  public static void printPrettyJson(Object obj) {
    JSONObject json = new JSONObject(obj.toString());
    System.out.println(json.toString(2));
  }

}
