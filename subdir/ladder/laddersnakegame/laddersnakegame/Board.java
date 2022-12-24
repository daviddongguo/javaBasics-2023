package laddersnakegame;

import java.util.HashMap;

public class Board {

  int[] ladderAndSnakePositionArray;

  public Board() {
    this.ladderAndSnakePositionArray = createLadderAndSnakePositionArray();
  }




  public int[] createLadderAndSnakePositionArray() {
    int[] array = new int[Setting.BROAD_SIZE + Setting.MAX_VALUE_OF_DICE];

    // 0, 1, ... 100
    for (int i = 0; i <= Setting.BROAD_SIZE; i++) {
      array[i] = i;
    }

    // 101, 102, ... 105
    for (int i = Setting.BROAD_SIZE + 1; i < Setting.BROAD_SIZE + Setting.MAX_VALUE_OF_DICE; i++) {
      // (200 - 101), (200 - 102), ... (200 - 105)
      array[i] = Setting.BROAD_SIZE * 2 - i;
    }

    // 4 -> 14
    getLadderAndSnake().forEach((key, value) -> array[key] = value);
    return array;
  }

  private HashMap<Integer, Integer> getLadderAndSnake() {
    HashMap<Integer, Integer> map = new HashMap<>(20);
//    map.put(105, 95);
//    map.put(104, 96);
//    map.put(103, 97);
//    map.put(102, 98);
//    map.put(101, 99);
    map.put(98, 78);
    map.put(97, 76);
    map.put(95, 24);
    map.put(93, 68);
    map.put(80, 100);
    map.put(79, 19);
    map.put(71, 91);
    map.put(64, 60);
    map.put(51, 67);
    map.put(48, 30);
    map.put(36, 44);
    map.put(28, 84);
    map.put(21, 42);
    map.put(16, 6);
    map.put(9, 51);
    map.put(4, 14);
    //  ruleMap.put(1, 38);
    return map;
  }
}
