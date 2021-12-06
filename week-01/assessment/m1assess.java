import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Scanner;
public class m1assess {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Welcome to Captain Capsules");
        System.out.println("=".repeat(30));
        System.out.print("Enter the number of capsules: ");

        int capsules = Integer.parseInt(console.nextLine());
        String[] rooms = new String[capsules + 1];

        System.out.printf("%nThere are %s capsules ready to be booked!%n" , capsules);

        String input;
        String yesNo = "n";
        String x = "empty string";

       do {
        System.out.println("Main Menu");
        System.out.println("=".repeat(30));
        System.out.println("1. Check-In");
        System.out.println("2. Check-Out");
        System.out.println("3. Display Capsules");
        System.out.println("4. Exit");

        System.out.print("Choose [1 - 4]: ");
        input = console.nextLine();

        if (input.equals("1")){
            addGuest(console, rooms);
        }else if (input.equals("2")){
            checkOutGuest(console, rooms);
        }else if (input.equals("3")){
            displayGuests(console, rooms);
        }else if (input.equals("4")){
            System.out.println("All progress will be lost.");
            System.out.println("Are you sure you want to exit [y/n]?");
            yesNo = console.nextLine();
            if (yesNo.equalsIgnoreCase("y")){
                System.out.println("Goodbye");
            }
        } else {
            System.out.println("I'm afraid I don't understand");
        }
        }while (yesNo.equalsIgnoreCase("n"));
    }

    static void checkOutGuest(Scanner console, String[] rooms){
        System.out.printf("Capsule number of guest checking out: ");
        int capsuleNumber = Integer.parseInt(console.nextLine());

        if (capsuleNumber < 1 || capsuleNumber > rooms.length){
            System.out.println("Not a valid capsule number");
        }else if ((rooms[capsuleNumber] == null))
        {
            System.out.println("Capsule is already empty");
        }else {
            rooms[capsuleNumber] = null;
            System.out.println("Success!");
        }
    }



    static void addGuest(Scanner console, String[] rooms){
        System.out.println("Guest Check-in");

        System.out.printf("Enter Guest Name: ");
        String guestName = console.nextLine();

        System.out.printf("Enter capsule number: ");
        int capsuleNumber = Integer.parseInt(console.nextLine());

        if (capsuleNumber < 1 || capsuleNumber > rooms.length){
                System.out.println("Not a valid room number");
        }else if ((rooms[capsuleNumber] != null)){
            System.out.println("That capsule is occupied");
        } else {
                rooms[capsuleNumber] = guestName;
                System.out.printf("Capsule #%s :%s. %nSuccess!%n", capsuleNumber, guestName);
            }
    }

    static void displayGuests(Scanner console, String[] rooms){
        System.out.println("Capsule Display");
        System.out.println("What capsule would you like to display: ");
        int x = Integer.parseInt(console.nextLine());
        for (int i = x - 5; i < x + 6; i++){
            if (i >= 1 && i < rooms.length){
                System.out.printf("Capsule #%s: %s %n",
                        i, rooms[i] == null ? "[Vacant]" : rooms[i]);
            }
        }

    }
}

