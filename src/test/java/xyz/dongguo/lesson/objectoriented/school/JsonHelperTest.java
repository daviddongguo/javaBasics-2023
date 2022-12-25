package xyz.dongguo.lesson.objectoriented.school;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.gson.Gson;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JsonHelperTest {

  private final String CHILD_02_NAME = "child102";
  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
  private MockClass parent;
  private MockClass child101;
  private MockClass child102;

  @BeforeEach
  void InitEach() {
    child101 = new MockClass.Builder("child101").number(101).build();
    child102 = new MockClass.Builder(CHILD_02_NAME).number(102).build();
    parent = new MockClass.Builder("parent").number(100).list(List.of(child101, child102)).build();
  }

  @Test
  void toPrettyJsonString() {
    String jsonString = JsonHelper.toPrettyJsonString(parent);
    assertNotNull(jsonString);
    assertTrue(jsonString.startsWith("{\n"));
    var stringArray = jsonString.split("\n");
    assertTrue(stringArray.length >= 2);
    assertEquals("{", stringArray[0]);
    assertEquals("}", stringArray[stringArray.length - 1]);

    JSONObject parent = new JSONObject(jsonString);
    var obj = parent.get("list");
    var string = obj.toString();
    JSONArray array = new JSONArray(string);
    JSONObject child01 = (JSONObject) array.get(1);
    var name = child01.get("name");
    assertEquals(CHILD_02_NAME, name);

    MockClass[] mockArray = (new Gson()).fromJson(string, MockClass[].class);
    var name2 = mockArray[1].name;
    assertEquals(CHILD_02_NAME, name2);


    System.out.println(jsonString);
  }

  @Test
  void arrayToPrettyJsonString() {
    List<MockClass> list = new ArrayList<>(List.of(parent, child101, child102));
    String jsonString = JsonHelper.toPrettyJsonString(list);
    assertNotNull(jsonString);
    assertTrue(jsonString.startsWith("{\n"));
    System.out.println(jsonString);
  }

  @Test
  void isNullOrEmpty() {
    assertTrue(JsonHelper.isNullOrEmpty(null));
    assertTrue(JsonHelper.isNullOrEmpty(""));
    assertTrue(JsonHelper.isNullOrEmpty("     "));
    assertFalse(JsonHelper.isNullOrEmpty("not null nor empty"));
  }

  @Test
  void generateRandomString() {
    int length = 120;
    String randomString = JsonHelper.generateRandomString(new Random(), length);
    assertEquals(length, randomString.length());
    char[] array = randomString.toCharArray();
    for (char c : array) {
      assertTrue(Character.isLetterOrDigit(c));
    }
    System.out.println(randomString);
  }

  @Test
  void toJsonStringOnSingleLine() {
    String jsonString = JsonHelper.toJsonStringOnSingleLine(parent);
    assertNotNull(jsonString);
    assertTrue(jsonString.startsWith("{"));
    assertTrue(jsonString.endsWith("}"));
    var stringArray = jsonString.split("\n");
    assertEquals(1, stringArray.length);
  }

  @Test
  void allToJsonString() {
    String jsonString = JsonHelper.allToJsonString(parent, child101, child102).trim();
    assertNotNull(jsonString);
    assertNotNull(jsonString);
    assertTrue(jsonString.startsWith("{"));
    assertTrue(jsonString.endsWith("}"));
    System.out.println(jsonString);
  }

  @Test
  void testPrintJson() {
    JsonHelper.printJson(parent, System.out);
    assertTrue(true);
  }

  @Test
  void testToPrettyJsonString() {
    String jsonString = JsonHelper.toPrettyJsonString(parent);
    assertNotNull(jsonString);
    assertNotNull(jsonString);
    assertTrue(jsonString.startsWith("{"));
    assertTrue(jsonString.endsWith("}"));
  }

  @Test
  void JSONObjectToArray() {
    String string = "{\"0\":\"value\",\"1\":5,\"2\":-2.345E68,\"3\":true}";
    var array = JsonHelper.JSONObjectToArray(string);
    assertNotNull(array.get(0));
    assertNotNull(array.get(1));
    assertNotNull(array.get(2));
    assertNotNull(array.get(3));
  }

  @Test
  void testPrintJsonOnSingleLine() {
    JsonHelper.printJsonOnSingleLine(parent, new PrintStream(outputStreamCaptor));
    var jsonString = outputStreamCaptor.toString();
    assertNotNull(jsonString);
    assertTrue(jsonString.startsWith("{"));
    assertTrue(jsonString.contains(CHILD_02_NAME));
  }

  @Test
  void testPrintAll() {
    JsonHelper.printAll(new PrintStream(outputStreamCaptor), parent, child101, child102);
    var jsonString = outputStreamCaptor.toString();
    assertNotNull(jsonString);
    assertTrue(jsonString.startsWith("{"));
    assertTrue(jsonString.contains(CHILD_02_NAME));
  }

  @Test
  void printJson() {
    JsonHelper.printJson(parent, new PrintStream(outputStreamCaptor));
    var jsonString = outputStreamCaptor.toString();
    assertNotNull(jsonString);
    assertTrue(jsonString.startsWith("{"));
    assertTrue(jsonString.contains(CHILD_02_NAME));
  }

  private static class MockClass {

    private final String name;
    private final List<MockClass> list;
    private final int number;

    private MockClass(Builder builder) {
      name = builder.name;
      number = builder.number;
      list = builder.list;
    }

    public String getName() {
      return name;
    }

    public List<MockClass> getList() {
      return list;
    }

    public int getNumber() {
      return number;
    }

    public static class Builder {

      private final String name;
      private final List<MockClass> list = new ArrayList<>();
      private int number;

      public Builder(String name) {
        this.name = name;
      }

      public Builder number(int value) {
        number = value;
        return this;
      }

      public Builder list(List<MockClass> list) {
        this.list.addAll(list);
        return this;
      }

      public MockClass build() {
        return new MockClass(this);
      }
    }
  }
}