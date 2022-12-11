package xyz.dongguo.effective;

import java.math.BigInteger;
import java.util.Random;

/**
 * @author dongg
 */
public class Chapter2 {

  public static void main(String[] args){
    Boolean a = Boolean.TRUE;
    BigInteger b = BigInteger.probablePrime(7, new Random());

    System.out.println(a);
    System.out.println(b);
  }

}
