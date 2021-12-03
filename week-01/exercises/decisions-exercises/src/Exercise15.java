import java.util.Scanner;

public class Exercise15 {

    public static void main(String[] args) {
        // SWITCH OPPOSITES
        // Given a word, print its opposite using a switch statement.
        Scanner console = new Scanner(System.in);

        System.out.print("Enter a word: ");
        String word = console.nextLine();
        String opposite = null;

        switch (word) {
            case "big":
        System.out.println("small");
        break;
            case "left":
        System.out.println("right");
        break;
            default:
        System.out.println("I don't know that word!");
        break;
        }

        // 1. Re-implement Exercise08 using a switch statement.
    }
}
