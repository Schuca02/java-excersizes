import java.util.Random;

public class Exercise12 {

    public static void main(String[] args) {
        int[] values = makeRandomArray();

        // 1. Count the number of positive and non-positive elements in `values`.
        // 2. Create two new int[]s, one for positive elements and one for non-positive.
        // (We count first to determine the capacity to allocate.)
        // 3. Loop through `values` a second time. If an element is positive, add it to the positive array.
        // If it is non-positive, add it to the non-positive array.
        // 4. Confirm that your secondary arrays are properly populated either by debugging or printing their elements.
        int positiveElements = 0;
        int negativeElements = 0;


        for (int i = 0; i < values.length; i++) {
            if (values[i] > 0) {
                positiveElements++;
            }else {
                negativeElements++;
            }


        }
        int[] updatePositive = new int[positiveElements];
        int [] updateNegative = new int [negativeElements];
        int y = 0;
        int z = 0;
        for (int x = 0; x < values.length; x++) {
            if (values[x] > 0) {
                updatePositive[y] = values[x];

                System.out.println(updatePositive[y]);
                y++;

            }else {
                updateNegative[z] = values[x];
                System.out.println(updateNegative[z]);
                z++;
            }


        }

    }

    public static int[] makeRandomArray() {
        Random random = new Random();
        int[] result = new int[random.nextInt(100) + 50];
        for (int i = 0; i < result.length; i++) {
            result[i] = random.nextInt(1000) - 500;
        }
        return result;
    }
}
