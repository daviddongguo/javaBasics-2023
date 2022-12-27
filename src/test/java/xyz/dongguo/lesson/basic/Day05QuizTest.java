package xyz.dongguo.lesson.basic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.javafaker.Faker;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Day05QuizTest {

  Day05Quiz solution;
  InputStream mockInput;
  Faker faker;

  @BeforeEach
  void initEach() {
    faker = new Faker();
  }

  @Test
  void printInput() {
    String expected = "a string for debugging";
    System.out.println(expected);
    InputStream mockInput = new ByteArrayInputStream(expected.getBytes());
    ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    solution = new Day05Quiz(mockInput, new PrintStream(outputStreamCaptor));
    solution.printInput();
    String result = outputStreamCaptor.toString();
    System.out.println(result);
    assertTrue(result.contains(expected));
  }

  @Test
  void calculateNumberUntilQuizzed() {
  }

  @Test
  void guessRandomNumber() {
  }

  @Test
  void guessNumber() {
  }

  @Test
  void subtractionQuiz() {
  }

  @Test
  void getRandomNumberBetween0And9() {
  }

  @Test
  void getRandomNumber() {
  }

  @Test
  void additionQuiz() {
  }
}