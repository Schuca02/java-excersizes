import java.util.Locale;
import java.util.Scanner;

public class Exercise07 {

    public static void main(String[] args) {

        String workPhrase = "Time to do homework.";
        String freePhrase = "I'm taking the day off!";

        Scanner console = new Scanner(System.in);
        System.out.print("Hours of homework: ");
        int hoursOfHomework = Integer.parseInt(console.nextLine());

        System.out.println("Day of the week: ");
        String dayOfWeek = console.nextLine()
                .toLowerCase(Locale.ROOT);

        char result = dayOfWeek.charAt(0);

        if (result == 's' && hoursOfHomework < 15){
            System.out.println("Hours of homework: " + hoursOfHomework);
            System.out.println("Day of week: " + dayOfWeek);
            System.out.println(freePhrase);
        } else if(result == 's' && hoursOfHomework >= 15){
            System.out.println("Hours of homework: " + hoursOfHomework);
            System.out.println("Day of week: " + dayOfWeek);
            System.out.println(workPhrase);
        }else if(!(result == 's')){
            System.out.println("Hours of homework: " + hoursOfHomework);
            System.out.println("Day of week: " + dayOfWeek);
            System.out.println(workPhrase);
        }
    }
}
