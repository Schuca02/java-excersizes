public class Exercise05 {

    public static void main(String[] args) {
        // 1. Declare an array to hold the names of the world's continents.
        // Do not use array literal notation. Allocate space for 6 continents and then set each value by index.
        // 2. Loop over each element and print it.
        String[] continents = new String[6];

        continents[0] = "Africa";
        continents[1] = "Asia";
        continents[2] = "Australia";
        continents[3] = "Europe";
        continents[4] = "North America";
        continents[5] = "South America";

        for (int index = 0; index < continents.length; index++){
            System.out.println(continents[index]);
        }
    }
}
