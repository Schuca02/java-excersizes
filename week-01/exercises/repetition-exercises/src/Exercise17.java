import java.util.Scanner;
public class Exercise17 {

    public static void main(String[] args) {
        // USER-DEFINED BOX
        // 1. Collect the following from a user: rows, columns, box character, border character.
        // 2. Use nested loops to print a user-defined box in the console.
        // (See Exercise16.)
        Scanner console = new Scanner(System.in);

        System.out.printf("What Border Character Do You Want: ");
        String symbol = console.nextLine();
        System.out.printf("What Box Character Do You Want: ");
        String box = console.nextLine();

        System.out.printf("How Many Rows Do You Wish To Have: ");
        int rows = Integer.parseInt(console.nextLine());

        System.out.printf("How Many Columns Do You Wish To Have: ");
        int columns = Integer.parseInt(console.nextLine());

        for (int i = 0; i < rows; i++) {
            if (i == 0 || i == rows - 1) {
                for (int x = 0; x < columns; x++) {
                    System.out.printf("%s", symbol);
                }
            } else {
                for (int y = 0; y < columns; y++) {
                    if (y == 0 || y == columns - 1){
                        System.out.printf("%s", symbol);

                    }else {
                        System.out.printf("%s", box);
                    }

                }
            }
            System.out.println("");
        }
    }
}

