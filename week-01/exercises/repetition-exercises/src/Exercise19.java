import java.util.Scanner;

public class Exercise19 {
    public static void main(String[] args) {
        // INTERLEAVE
        Scanner console = new Scanner(System.in);

        System.out.print("First string: ");
        String first = console.nextLine();

        System.out.print("Second string: ");
        String second = console.nextLine();

        // 1. Write a loop to interleave two strings to form a new string.
        // To interleave, during each loop take one character from the first string and add it to the result
        // and take one character from the second string and add it to the result.
        // If there are no more characters available, don't add characters.
        // 2. Print the result.

        // Examples
        // "abc", "123" -> "a1b2c3"
        // "cat", "dog" -> "cdaotg"
        // "wonder", "o" -> "woonder"
        // "B", "igstar" -> "Bigstar"
        // "", "huh?" -> "huh?"
        // "wha?", "" -> "wha?"

        if (first.length() < second.length()) {
            for (int i = 0; i < first.length(); i++) {


                System.out.print(first.charAt(i));
                System.out.print(second.charAt(i));

            }
            System.out.print(second.substring(first.length()));

        } else {
            for (int i = 0; i < second.length(); i++) {

                System.out.print(first.charAt(i));
                System.out.print(second.charAt(i));

            }
            System.out.print(first.substring(second.length()));

        }

    }
}






