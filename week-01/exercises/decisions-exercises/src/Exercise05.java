import java.util.Scanner;

public class Exercise05 {

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        System.out.print("Enter the secret word: ");
        String secret = console.nextLine();
        if (secret.equalsIgnoreCase("tahini")){
            System.out.println("That's the sauce!");
        }else{
            System.out.println("That's not quite right");
        }

        // 1. Add decision statements so that:
        // If the secret word is tahini, print the message:
        //   You're correct. The secret word is "tahini".
        // Otherwise, print:
        //   That's not quite right. Try again.
    }
}
