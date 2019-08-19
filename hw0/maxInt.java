public class maxInt {
    /** Returns the maximum value from m. */
    public static int max(int[] m) {
        int num = 0;
        int idx = 0;
        while (idx < m.length){
          if (m[idx] > num) {
            num = m[idx];
          }
          idx = idx + 1;
        }
        return num;
    }
    public static void main(String[] args) {
       int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};
       System.out.print(max(numbers));
    }
}
