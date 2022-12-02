package xyz.dongguo.lesson;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Day06 {

  private final Scanner scanner;
  private final PrintStream out;

  public Day06(InputStream in, PrintStream out) {
    scanner = new Scanner(in);
    this.out = out;
  }

  public void run() {
    //    printInput();
    //    printFirst50PrimeNumbers();
    //    printOccurrenceOfLetter("   A*&^123aABB    c   ");
    //    printOccurrenceOfLetter("PalindromeIgnoreNonAlphanumeric");
    //    printPalindromes();
    //    isPrime(23);
  }

  private void printPalindromes() {
    String inputString = "M*adam";
    out.printf("\"%s\" %s a palindrome%n", inputString, isPalindromes(inputString) ? "is" : "is not");
    inputString = "lady";
    out.printf("\"%s\" %s a palindrome%n", inputString, isPalindromes(inputString) ? "is" : "is not");
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

  private void printOccurrenceOfLetter(String inputString) {
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
    int number = 1;
    int count = 1;
    while (count <= 50) {
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
    if (number == 1 || number == 2) {
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

  public void printInput() {
    out.printf("%nPlease enter any string: ");
    out.println(scanner.nextLine());
  }

  public boolean isSumExisted(int[] array, int left, int right, int sum) {
    return array[left] == sum || isSumExisted(array, left + 1, right, sum - array[left]);
  }

}
