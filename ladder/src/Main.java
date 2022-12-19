import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    System.out.println("Hello world!");
    int height = 15;
    List<String> list1 = generateWave(height, true);
    List<String> list2 = generateWave(height -3, false);
    List<String> list3 = generateWave(height - 3, true);

    List<String> list = new ArrayList<>();
    int size = list1.size();
    for (int i = 0; i < size; i++) {
      int off2 = size - list2.size();
      String string2 = "";
      if(off2 <= i){
        string2 = list2.get(i - off2);
      }
      int off3 = size - list3.size();
      String string3 = "";
      if(off3 <= i){
        string3 = list3.get(i - off3);
      }
      list.add(list1.get(i) + string2 + string3)  ;
    }

    for (int i = 0; i < list.size(); i++) {
      System.out.println(list.get(i));
    }

  }

  private static List<String> generateWave(int height, boolean isDown) {
    List<String> list = new ArrayList<>(height);
    for (int y = 0; y < height; y++) {
      int width = height;
      double yy = (double) y;
      double xx = Math.asin(yy / height) * height * 0.859D;
      int x = (int) xx;
      System.out.println(x);
      if (isDown) {
        list.add(String.format("%s%s%s", " ".repeat(x), "6", " ".repeat(width - x)));
      } else {
        if (y == height - 1) {
          list.add("");
        } else {
          list.add(String.format("%s%s%s", " ".repeat(width - x), "6", " ".repeat(x)));
        }
      }
    }

    return list;
  }

}