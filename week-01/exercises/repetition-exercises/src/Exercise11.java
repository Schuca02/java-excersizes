import java.util.Scanner;
public class Exercise11 {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Starting number: ");
        int start = console.nextInt();

        System.out.println("Increment: ");
        int increment = console.nextInt();

        System.out.println("Ending number");
        int end = console.nextInt();
        int sum = 0;

        for (int i = start; i <= end; i += increment){
            System.out.println(i);
        }
        for (int x = start; x <= end; x += increment){
            sum += x;
            System.out.println(sum);
        }

    }
}
