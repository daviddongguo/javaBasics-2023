package xyz.dongguo.interview;

public class BestTimeToBuyAndSellStock {

  public static void main(String[] args) {

    int[] prices = {7, 1, 5, 3, 6, 4};

    System.out.println(maxProfit(prices));

  }

  private static int maxProfit(int[] prices) {
    int maxProfit = 0;
    int minPrice = prices[0];
    for (int i = 1; i <= prices.length - 1; i++) {
      if (prices[i] < minPrice) {
        minPrice = prices[i];
        continue;
      }

      if (prices[i] - minPrice > maxProfit) {
        maxProfit = prices[i] - minPrice;
      }
    }
    return maxProfit;
  }
}
