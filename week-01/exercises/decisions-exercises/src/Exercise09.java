import java.util.Scanner;
public class Exercise09 {
    public static void main(String[] args) {
    Scanner console = new Scanner(System.in);

    System.out.printf("Enter a minimum number: ");
    int a = console.nextInt();

    System.out.printf("Enter a maximum number: ");
    int b = console.nextInt();

    System.out.printf("Enter a final number: ");
    int c = console.nextInt();

    if (c > a && c < b){
        System.out.printf("%s is between %s and %s", c, b, a);
    }else{
        System.out.printf("%s is not between %s and %s", c, b, a);
    }


    }
}
