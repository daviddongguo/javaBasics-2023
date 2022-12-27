package xyz.dongguo.lesson.basic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Day01DisplayOnlyTest {

  ByteArrayOutputStream outputStreamCaptor;
  Day01DisplayOnly solution;

  @BeforeEach
  void initEach() {
    outputStreamCaptor = new ByteArrayOutputStream();
    solution = new Day01DisplayOnly(new PrintStream(outputStreamCaptor));
  }

  @Test
  void printTable() {
    solution.printTable();
    var result = outputStreamCaptor.toString();
    String expected = "____________________________________________________________\n"
       + "|      Name      |        VIP         |      Balance       |\n"
       + "------------------------------------------------------------\n"
       + "|    Dongguo     |       true         |        +1,234.56   |\n"
       + "|   Anonymous    |       false        |  +998,855,663.21   |\n"
       + "|      Rich      |       false        |   -88,995,566.99   |\n"
       + "____________________________________________________________\n";
    assertEquals(removeLineEnd(expected), removeLineEnd(result));
  }

  private String removeLineEnd(String string) {
    return string.trim().replace("\n", "").replace("\r", "");
  }

  @Test
  void getRandomBetweenMinAndMax() {
    int counter = 1000;
    int min = 5;
    int max = 125;
    int counterOfMin = 0;
    int counterOfMax = 0;
    int counterOfExcludeValue = 0;
    for (int i = 0; i < counter; i++) {
      int value = solution.getRandomBetweenMinAndMax(min, max, solution.getRandom());
      if (value < min || value > max) {
        counterOfExcludeValue++;
        continue;
      }
      if (value == min) {
        counterOfMin++;
        continue;
      }
      if (value == max) {
        counterOfMax++;
      }
    }

    assertEquals(0, counterOfExcludeValue);
    assertTrue(counterOfMin > 1);
    assertTrue(counterOfMax > 1);
  }

  @Test
  void printSumOfNumberSeries() {
    solution.printSumOfNumberSeries();
    var result = outputStreamCaptor.toString();
    System.out.println(result);
    assertTrue(true);
  }

  @Test
  void printSumOfFloatNumberSeries() {
    solution.printSumOfFloatNumberSeries();
    var result = outputStreamCaptor.toString();
    System.out.println(result);
    assertTrue(true);
  }

  @Test
  void printMultiplicationTable() {
    solution.printMultiplicationTable(9);
    var result = outputStreamCaptor.toString();
    System.out.println(result);
    String expected = "               MULTIPLICATION TABLE\n"
       + "\n"
       + "   |     1    2    3    4    5    6    7    8    9\n"
       + "--------------------------------------------------\n"
       + " 1 |     1    2    3    4    5    6    7    8    9\n"
       + " 2 |     2    4    6    8   10   12   14   16   18\n"
       + " 3 |     3    6    9   12   15   18   21   24   27\n"
       + " 4 |     4    8   12   16   20   24   28   32   36\n"
       + " 5 |     5   10   15   20   25   30   35   40   45\n"
       + " 6 |     6   12   18   24   30   36   42   48   54\n"
       + " 7 |     7   14   21   28   35   42   49   56   63\n"
       + " 8 |     8   16   24   32   40   48   56   64   72\n"
       + " 9 |     9   18   27   36   45   54   63   72   81";
    assertEquals(removeLineEnd(expected), removeLineEnd(result));
  }

  @Test
  void printYearsOfDoubleTuition() {
    solution.printYearsOfDoubleTuition();
    String result = outputStreamCaptor.toString();
    System.out.println(result);
    assertTrue(result.contains("11 years"));
  }

  @Test
  void printYearsAfterTimes() {
    solution.printYearsAfterTimes(10);
    String result = outputStreamCaptor.toString();
    System.out.println(result);
    assertTrue(result.contains("35 years"));
  }

  @Test
  void calculateCompoundInterest() {
    var result = solution.calculateCompoundInterest(0.1D, 11);
    assertTrue(result - 1.85 < 0.01);

  }
}