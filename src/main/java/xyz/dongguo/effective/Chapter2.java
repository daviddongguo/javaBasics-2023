package xyz.dongguo.effective;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;

public class Chapter2 {
  Boolean a = Boolean.valueOf(true);
  Boolean b = Boolean.TRUE;

  public static void main(String[] args){
    BigInteger b = BigInteger.probablePrime(7, new Random());

    System.out.println(b);
  }

}
