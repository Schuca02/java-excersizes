public class Exercise08 {

    // 1. Create a method.
    // Name: getRandomFruit
    // Inputs: none
    // Output: String
    // Description: returns a random fruit name as a string.
    // See Exercise01.
    // Choose from at least 5 fruit.

    public static void main(String[] args) {

        String randomFruit = getRandomFruit();
        System.out.println(randomFruit);

    }
        public static String getRandomFruit(){

            switch ((int) (Math.random() * 6)) {
                case 0:
                    return "Strawberry";
                case 1:
                    return "Pinapple";
                case 2:
                    return "Blueberry";
                case 3:
                    return "Blackberry";
                case 4:
                    return "Banana";
                case 5:
                    return "Mango";
                case 6:
                    return "Dragonfruit";
            }

            return "";
        }
    }


        // 2. Call your method in various ways to test it here.


