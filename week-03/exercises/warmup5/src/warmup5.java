import java.util.Scanner;

public class warmup5 {

    public static String translateToPigLatin(String input) {
        char firstLetter = input.charAt(0);
        char secondLetter = input.charAt(1);
        if (firstLetter == 'a' || firstLetter == 'e' || firstLetter == 'i'
                || firstLetter == 'o' || firstLetter == 'u') {
            String output = input + "ay";
            return String.valueOf(System.out.printf("%s translates to %s", input, output));
        }

        if (!(secondLetter == 'a' || secondLetter == 'e' || secondLetter == 'i'
                || secondLetter == 'o' || secondLetter == 'u')) {
            String everythingElse = input.substring(2);
            String output = everythingElse + firstLetter + secondLetter + "ay";
            return String.valueOf(System.out.printf("%s translates to %s", input, output));
        }

        String everythingElse = input.substring(1);
        String output = everythingElse + firstLetter + "ay";
        return String.valueOf(System.out.printf("%s translates to %s", input, output));
    }

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        System.out.println("Enter a word or phrase to be translated to Pig Latin: ");
        String input = console.nextLine();
        translateToPigLatin(input);

    }
}
