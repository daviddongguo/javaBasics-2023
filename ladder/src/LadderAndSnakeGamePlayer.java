public class LadderAndSnakeGamePlayer extends Player {

  /**
   * the position in the board, 0-100, 101-105
   */
  int position = Setting.PLAYER_START_POSITION;
  /**
   * store the order and icon information
   */
  PlayerCharEnum orderOfStart = PlayerCharEnum.FIRST;
  int diceValue = 0;
  public LadderAndSnakeGamePlayer(String name) {
    super(name);
  }

  public LadderAndSnakeGamePlayer(String name, int order) {
    super(name);
    this.orderOfStart = PlayerCharEnum.getByIndex(order);
  }

  public PlayerCharEnum getOrderOfStart() {
    return orderOfStart;
  }

  public void setOrderOfStart(int order) {
    this.orderOfStart = PlayerCharEnum.getByIndex(order);
  }

}
