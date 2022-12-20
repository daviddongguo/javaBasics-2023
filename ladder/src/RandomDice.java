import java.util.Random;

public class RandomDice implements IMovable {
  Random random;

  public RandomDice(Random random) {
    this.random = random;
  }

  @Override
  public int getMovingSteps() {
    int minDice = 1;
    int maxDice = 6;
    return minDice + random.nextInt(maxDice);
  }
}
