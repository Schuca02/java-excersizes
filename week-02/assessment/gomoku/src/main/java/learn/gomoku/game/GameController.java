package learn.gomoku.game;

import java.util.Scanner;

import learn.gomoku.players.Player;
import learn.gomoku.players.HumanPlayer;
import learn.gomoku.players.RandomPlayer;


public class GameController {
    private Gomoku game;
    private Scanner console = new Scanner(System.in);
    private char[][] board = new char[Gomoku.WIDTH][Gomoku.WIDTH];

    public void run() {

        setUp(console);


        while (!game.isOver()) {
            System.out.println("It's your turn " + game.getCurrent().getName());
            printBoard();
            Stone stone = game.getCurrent().generateMove(game.getStones());
            if (stone == null) {
                int row = readInt("Enter a row: ", 1, 15) - 1;
                int column = readInt("Enter a column: ", 1, 15) - 1;
                stone = new Stone(row, column, game.isBlacksTurn());

            }
            Result result = game.place(stone);
            if (!result.isSuccess()) {
                System.out.println("Err: " + result.getMessage());
            }
        }

        if (game.getWinner() == null) {
            System.out.println("It's a tie!");
        } else {
            System.out.println("The winner is: " + game.getWinner().getName());
        }


        playAgain("Do you want to play again? [y/n] ");


    }

    private void printBoard() {
        for (Stone s : game.getStones()) {
            board[s.getRow()][s.getColumn()] = s.isBlack() ? 'X' : 'O';
        }
        for (int row = 0; row < Gomoku.WIDTH; row++) {
            for (int col = 0; col < Gomoku.WIDTH; col++) {
                if (board[row][col] == 0) {
                    System.out.print("_");
                } else {
                    System.out.print(board[row][col]);
                }
            }
            System.out.println();
        }

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
            theInput = readRequiredString(message);
            if (theInput.length() > 0) {
                valid = validateTheInputIsANumber(theInput);
            }
        }
        while (!valid);


        return Integer.parseInt(theInput);
    }

    private void setUp(Scanner console) {
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

