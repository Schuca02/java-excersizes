public class Exercise06 {

    static boolean isBetween(int a, int b, int c) {
        if (a < b && b < c) {
            return (true);
        }return false;
    }
        // 1. Create a method.
        // Name: isBetween
        // Inputs: int, int, int
        // Output: boolean
        // Description: return true if the first parameter is between the second and third parameter.
        // Otherwise, returns false.

        public static void main(String[] args){
            // 2. Call your method in various ways to test it here.
            System.out.println(isBetween(23, 24, 25));
            System.out.println(isBetween(25, 26, 22));

        }
    }