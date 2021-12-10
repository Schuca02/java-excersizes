import java.util.Scanner;

public class Exercise05 {

    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);

        Musician[] musicians = new Musician[5];
        musicians[0] = new Musician();
        musicians[1] = new Musician();
        musicians[2] = new Musician();
        musicians[3] = new Musician();
        musicians[4] = new Musician();
        System.out.println("Enter your top 5 musicians: ");

for (int x = 0; x < musicians.length; x++){
    musicians[x].setName(console.nextLine());
}


        // 1. Use a loop to populate the `musicians` array with your top 5 favorite musicians.
        // (Replace Frank Ocean.)
        // Create musicians from user input. (See Exercise04.)
for (int i = 0; i < musicians.length; i ++){
    System.out.print("Favorite musicians are: " + musicians[i].getName() + ", ");
}
        // 2. Use a second loop to print details about each musician.
    }
}
