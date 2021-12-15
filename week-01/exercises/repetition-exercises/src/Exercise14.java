import java.util.Scanner;
public class Exercise14 {

    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);

        System.out.println("Enter a phrase: ");
        String phrase = console.nextLine();
        int digitNumber = 0;

        for (int i = 0; i < phrase.length(); i++) {
            char isDigit = phrase.charAt(i);


            if (Character.isDigit(isDigit)){
                digitNumber ++;
            }
        }
        System.out.println("Number of digits is: " + digitNumber);
        // 1. Collect a phrase from a user via the console.
        // 2. Count the number of digits in the phrase.
        // Hint: Character.isDigit
        // 3. Print the result.
    }
}
