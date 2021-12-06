public class Exercise07 {
    static boolean areInOrder(int a, int b, int c, int d){
        if (a < b && b < c && c < d){
            return true;
        }return false;
    }

    // 1. Create a method.
    // Name: areInOrder
    // Inputs: int, int, int, int
    // Output: boolean
    // Description: return true if the four parameters are in ascending order.
    // Otherwise, returns false.

    public static void main(String[] args) {
        System.out.println(areInOrder(23, 24, 25, 26));
        System.out.println(areInOrder(25, 26, 22, 28));
        boolean result = areInOrder(1,2,5,6);
        System.out.println(result);
        // 2. Call your method in various ways to test it here.
    }
}
