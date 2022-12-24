package laddersnakegame;

public enum PlayerCharEnum {
      /**
     * 0, ◐
     */
    FIRST(0, '◐'),
    /**
     * 1, ◑
     */
    SECOND(1, '◓'),
    /**
     * 2, ◑
     */
    THIRD(2, '◑'),
    /**
     * 3, ◒
     */
    FORTH(3, '◒');
    private final int index;
    private final char icon;

    PlayerCharEnum(int index, char icon) {
      this.index = index;
      this.icon = icon;
    }

  /**
   * for example  0 -> FIRST
   * @param index
   * @return
   */
    public static PlayerCharEnum getByIndex(int index) {
      for (PlayerCharEnum player : PlayerCharEnum.values()) {
        if (player.getIndex() == index) {
          return player;
        }
      }
      return null;
    }

    public int getIndex() {
      return this.index;
    }

    public char getChar() {
      return this.icon;
    }
}
