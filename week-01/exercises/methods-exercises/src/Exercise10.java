public class Exercise10 {
    public static void main(String[] args) {
        String phrase = "Look at all the white space";
        System.out.println(phrase);
        System.out.println(whiteSpaceRemover(phrase));

    }


    public static String whiteSpaceRemover(String phrase) {
        String white = " ";
        String result = "";

        for (int i = 0; i < phrase.length(); i++)
            if (white.indexOf(phrase.charAt(i)) < 0){
                result += phrase.charAt(i);
            }
        return result;
    }
    // 1. Add a `main` method.
    // 2. Create method that accepts a String and returns that string with all of its whitespace remove.
    // 2. Call your method in various ways in the main method.
}




