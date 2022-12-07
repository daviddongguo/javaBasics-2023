package xyz.dongguo.lesson;

import java.io.PrintStream;

/**
 * @author dongg
 */
public class Day06 {

  private final PrintStream out;

  public Day06(PrintStream out) {
    this.out = out;
  }

  public boolean isPalindromes(String inputString) {
    String formattedString = inputString.trim().toUpperCase();
    formattedString = filter(formattedString);
    int leftIndex = 0;
    int rightIndex = formattedString.length() - 1;
    while (leftIndex < rightIndex) {
      if (formattedString.charAt(leftIndex) != formattedString.charAt(rightIndex)) {
        return false;
      }
      leftIndex++;
      rightIndex--;
    }
    return true;
  }

  public String filter(String sourceString) {
    StringBuilder outString = new StringBuilder();
    for (int i = 0; i < sourceString.length(); i++) {
      if (Character.isLetter(sourceString.charAt(i))) {
        outString.append(sourceString.charAt(i));
      }
    }
    return outString.toString();
  }

  public void printOccurrenceOfLetter(String inputString) {
    out.printf("the number of occurrence of each letter in %s as below: %n", inputString);
    char c = 'A';
    for (int i : countOccurrenceOfLetter(inputString)) {
      if (i != 0) {
        out.printf("%s\t\t%d%n", c, i);
      }
      c++;
    }
  }

  public int[] countOccurrenceOfLetter(String palindromeString) {
    int[] occurrenceOfLetters = new int[26];
    String formattedString = palindromeString.trim().toUpperCase();
    for (int i = 0; i < formattedString.length(); i++) {
      char charToCheck = formattedString.charAt(i);
      if (charToCheck >= 'A' && charToCheck <= 'Z') {
        occurrenceOfLetters[charToCheck - 'A']++;
      }
    }
    return occurrenceOfLetters;
  }

  public void printFirst50PrimeNumbers() {
    final int first50 = 50;
    int number = 1;
    int count = 1;
    while (count <= first50) {
      if (isPrime(number)) {
        out.printf("%5d", number);
        if (count % 10 == 0) {
          out.printf("%n");
        }
        count++;
      }
      number++;
    }
  }

  public boolean isPrime(int number) {
    int basePrime1 = 1;
    int basePrime2 = 2;
    if (number == basePrime1 || number == basePrime2) {
      return true;
    }
    int divisor = 2;
    while (divisor < number / divisor + 1) {
      if (number % divisor == 0) {
        return false;
      }
      divisor++;
    }
    return true;
  }

  public int getGreatestDivisor(int numberSmaller, int numberBigger) {
    if (numberSmaller > numberBigger) {
      int temp = numberSmaller;
      numberSmaller = numberBigger;
      numberBigger = temp;
    }
    while (numberSmaller < numberBigger) {
      if (numberBigger % numberSmaller == 0) {
        return numberSmaller;
      }
      int newNumberSmaller = numberBigger % numberSmaller;
      numberBigger = numberSmaller;
      numberSmaller = newNumberSmaller;
    }

    return 1;
  }

  public boolean isSumExisted(int[] array, int left, int right, int sum) {
    return array[left] == sum || isSumExisted(array, left + 1, right, sum - array[left]);
  }

}
