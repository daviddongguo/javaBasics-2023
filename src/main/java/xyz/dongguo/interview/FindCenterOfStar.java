package xyz.dongguo.interview;

public class FindCenterOfStar {
  public static void main(String[] args){
    System.out.println("hi, there");
  }

  public int find(int[][]  edges){

    if(edges[0][0]==edges[1][0]||edges[0][0]==edges[1][1]) {
      return edges[0][0];
    }
    return edges[0][1];
  }

}
