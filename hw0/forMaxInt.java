public class forMaxInt {
    /** Returns the maximum value from m using a for loop. */
    public static int forMax(int[] m) {
        int maxInt = 0;
        for (int i = 0; i < m.length; i += 1){
          maxInt = maxInt + m[i];
        }
        return maxInt;
    }
    public static void main(String[] args) {
       int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};
       System.out.println(forMax(numbers));
    }
}
