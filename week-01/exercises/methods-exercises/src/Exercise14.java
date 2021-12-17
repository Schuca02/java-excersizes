import java.util.Scanner;

public class Exercise14 {
    /* SHORT SURVEY

    Write a program that asks a user four questions and prints the results:
    - What is your first name?
    - What is your last name?
    - How many towns/cities have you lived in?
    - How many musical instruments can you play?

    Store each answer in a variable with an appropriate type.
    Print the results after the user has answered all four questions.

    Use methods to break the program into reusable blocks of code.
     */
    public static String readRequiredString(String prompt) {
        Scanner console = new Scanner(System.in);
        String result;
        do {
            System.out.print(prompt);
            result = console.nextLine();
        } while (result.length() == 0);
        return result;
    }

    public static int readRequiredInt(String prompt) {
        int count = Integer.parseInt(readRequiredString(prompt));
        return count;
    }


    public static void printResults() {
        String firstName = readRequiredString("What is your first name? ");
        String lastName = readRequiredString("What is your last name? ");
        int cities = readRequiredInt("How many cities have you lived in? ");
        String instrument = readRequiredString("What instrument do you play? ");

        System.out.printf("Name: %s%s%n# of Cities Lived: %s%nInstruments played: %s", firstName, lastName, cities, instrument);
    }

    public static void main(String[] args) {
        printResults();
    }
}