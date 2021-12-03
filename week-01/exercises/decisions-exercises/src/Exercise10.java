import java.util.Scanner;

public class Exercise10 {

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        System.out.printf("Welcome to the USPS.");
        System.out.println("How much does your package weigh (to the nearest lbs.)?");
        int i = console.nextInt();
        if (i < 1){
            System.out.println("Send it with an envelope! Thank you");
        }else if (i > 5){
            System.out.println("That wont fit in our trucks. Thank you!");
        }
        System.out.println("Where is your package headed? Zone 1-3: ");
        int place = console.nextInt();
        if (place > 3){
            System.out.println("We don't ship there. Sorry");
        }else if (place == 3){
            if (i == 5) {
                System.out.println("$11.30");
            }else if (i == 4) {
                System.out.println("$10.55");
            }else if (i == 3){
                System.out.println("$9.70");
            }else if (i == 2){
                System.out.println("$8.70");
            }else if (i == 1){
                System.out.println("$7.85");
            }
        }else if (place == 1 || place == 2) {
            if (i == 5) {
                System.out.println("$10.20");
            }else if (i == 4) {
                System.out.println("$9.20");
            }else if (i == 3) {
                System.out.println("$8.70");
            }else if (i == 2) {
                System.out.println("$8.25");
            }else if (i == 1) {
                System.out.println("$7.50");
            }
        }

        // USPS
        // Below is an abbreviated version of the US Postal Service retail parcel rates:
        /*
        Lbs | Zones 1&2 | Zone 3
        ===============
        1      $7.50      $7.85
        2       8.25       8.70
        3       8.70       9.70
        4       9.20      10.55
        5      10.20      11.30
        */

        // 1. Collect the parcel lbs and zone (1, 2, or 3) from the user.
        // 2. Add `if`/`else if`/`else` logic to cover all rates.
        // Use whatever strategy you think is best. You can create compound conditions or nest if/else statements.
        // If a lbs/zone combo does not exist, print a warning message for the user.


    }
}
