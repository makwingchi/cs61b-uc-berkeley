public class DrawTriangle {
  public static void main(String[] args){
    int times = 1;
    String stars = "*";
    while (times <= 5){
      System.out.println(stars);
      stars = stars + "*";
      times = times + 1;
    }
  }
}
