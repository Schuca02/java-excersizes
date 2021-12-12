package learn.gomoku.game;

import java.util.Scanner;

import learn.gomoku.players.Player;
import learn.gomoku.players.HumanPlayer;
import learn.gomoku.players.RandomPlayer;


public class GameController {
    public void run() {

        Scanner console = new Scanner(System.in);

        Gomoku game = setUp(console);

        while (!game.isOver()){
            System.out.println("It's your turn " + game.getCurrent().getName());
            Stone stone = game.getCurrent().generateMove(game.getStones());
            Result result = game.place(stone);
            if (!result.isSuccess()){
                System.out.println("Err: " + result.getMessage());
            }
        }
        if (game.getWinner() == null){
            System.out.println("It's a tie!");
        }else{
            System.out.println("The winner is: " + game.getWinner());
        }

//        System.out.println("readInt(\"Submit only ints or die\" , 15, 15) = " + readInt("Submit only ints or die", 15, 15));



        playAgain("Do you want to play again? [y/n] ");


    }

    public static String readRequiredString(String prompt) {
        Scanner console = new Scanner(System.in);
        String result;
        do {
            System.out.print(prompt);
            result = console.nextLine();
        } while (result.length() == 0);
        return result;
    }

    static final char[] digits = {
            '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9'
    };

    private static boolean isNumber(char theChar) {
        for (int j = 0; (j <= digits.length - 1); j++) {
            if (theChar == digits[j]) {
                return true;
            }
        }
        System.out.println("That's not a valid input");
        return false;
    }

    private static boolean validateTheInputIsANumber(String theInput) {
        boolean validateNextChar = true;

        for (int i = 0; (i <= theInput.length() - 1) && validateNextChar; i++) {
            char theChar = theInput.charAt(i);
            validateNextChar = isNumber(theChar);
        }
        return validateNextChar;
    }

    public static int readInt(String message, int min, int max) {
        String theInput;
        boolean valid = false;
        do {
            System.out.print("Enter the coordinates of your move: ");
            theInput = readRequiredString(message);
            if (theInput.length() > 0) {
                valid = validateTheInputIsANumber(theInput);
            }
        }
        while (!valid);


        return Integer.parseInt(theInput);
    }

    private Gomoku setUp(Scanner console) {
        System.out.println("Welcome to Gomoku");
        System.out.println("=".repeat(18) + "\n");

        System.out.println("Player 1 is:");
        System.out.println("1. Human");
        System.out.println("2. Random Player");
        System.out.printf("Select [1-2]: ");

        int choice = console.nextInt();
        console.nextLine();

        if (choice == 1) {
            System.out.printf("Enter your name: ");
            String name = console.nextLine();
            HumanPlayer player = new HumanPlayer(name);
            System.out.println("\nHello " + player.getName() + "\n");

        } else {
            RandomPlayer randomPlayer1 = new RandomPlayer();
            System.out.println("\n" + randomPlayer1.getName() + "\n");
        }

        System.out.println("Player 2 is:");
        System.out.println("1. Human");
        System.out.println("2. Random Player");
        System.out.printf("Select [1-2]: ");

        int choiceTwo = console.nextInt();
        console.nextLine();


        if (choiceTwo == 1) {
            System.out.printf("Enter your name: ");
            String name = console.nextLine();
            HumanPlayer player2 = new HumanPlayer(name);
            System.out.println("\nHello " + player2.getName() + "\n");

        } else {
            RandomPlayer randomPlayer2 = new RandomPlayer();
            System.out.println("\n" + randomPlayer2.getName());

        }
        Gomoku game = new Gomoku(HumanPlayer player.getName(), )
//Make the board
//        Call the Game (Player 1, Player 2) --Figure out how to get those Players in scope.


    }
}

