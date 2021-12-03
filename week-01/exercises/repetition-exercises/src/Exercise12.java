import java.util.Scanner;

public class Exercise12 {

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        System.out.print("Enter a phrase: ");
        String phrase = console.nextLine();
        String x = "x";
        String result = "";

            if (phrase.contains(x)) {
                System.out.println("Yeah it's there");
            } else {
                System.out.println("nah it ain't there");
            }
        }
        // 1. Write a loop to determine if the letter `x` occurs in a user-entered phrase.
        // 2. Print a message for both finding and not finding the `x`.

}
