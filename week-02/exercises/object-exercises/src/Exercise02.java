public class Exercise02 {

    public static void main(String[] args) {

        // 1. Add a getter for the rating field in Musician.

        Musician ocean = new Musician("Frank Ocean", 10);
        System.out.println(ocean.getName());
        // 2. Uncomment the line below and insure that it compiles and runs.
         System.out.println(ocean.getRating());

         Musician elliot = new Musician("Elliot Smith ", 9);
         System.out.printf(elliot.getName());
         System.out.println(elliot.getRating());

         Musician nickel = new Musician("Nickelback", 2);
         System.out.printf("%s: %s", nickel.getName(), nickel.getRating());

        // 3. Instantiate two musicians and assign them to new variables.
        // 4. Print each musicians' name and rating on a single line.
    }
}
