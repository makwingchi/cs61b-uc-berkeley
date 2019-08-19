public class triangleDrawer {
   public static void drawTriangle(int N) {
     int times = 1;
     String stars = "*";
     while (times <= N){
       System.out.println(stars);
       stars = stars + "*";
       times = times + 1;
     }
   }

   public static void main(String[] args) {
     drawTriangle(10);
   }
}
