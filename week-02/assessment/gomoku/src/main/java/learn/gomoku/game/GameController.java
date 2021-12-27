package learn.gomoku.game;

import learn.gomoku.players.HumanPlayer;
import learn.gomoku.players.Player;
import learn.gomoku.players.RandomPlayer;

import java.util.Scanner;


public class GameController {
    private Gomoku game;
    private Scanner console = new Scanner(System.in);
    private char[][] board = new char[Gomoku.WIDTH][Gomoku.WIDTH];

    public void run() {

        setUp(console);
        play();
        playAgain("Do you want to play again? [y/n] ");


    }

    private void play() {
        while (!game.isOver()) {
            System.out.println("It's your turn " + game.getCurrent().getName());
            printBoard();
            Stone stone = game.getCurrent().generateMove(game.getStones());
            if (isHuman(stone)) {
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
    }

    private boolean isHuman(Stone stone) {

        return stone == null;
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

            valid = theInput.length() > 0
                    && validateTheInputIsANumber(theInput)
                    && min <= Integer.parseInt(theInput)
                    && max >= Integer.parseInt(theInput);
            if (!valid) {
                System.out.println("That's not a valid input");
            }
        }
        while (!valid);


        return Integer.parseInt(theInput);
    }

    private void setUp(Scanner console) {
        System.out.println("Welcome to Gomoku");
        System.out.println("=".repeat(18) + "\n");

        Player player1 = getPlayer(console, "Player 1 is: ");
        Player player2 = getPlayer(console, "Player 2 is: ");

        game = new Gomoku(player1, player2);
        board = new char[Gomoku.WIDTH][Gomoku.WIDTH];

        System.out.println("(Randomizing)\n");
        System.out.println(game.getCurrent().getName() + " goes first!");

    }

    public boolean playAgain(String prompt) {
        String exit = readRequiredString(prompt);

        if(exit.equalsIgnoreCase("n")){
            game.isOver();
        }else {
            run();
        }

        System.out.println("Goodbye!");
        return true;

    }


    private Player getPlayer(Scanner console, String prompt) {
        System.out.println(prompt);
        System.out.println("1. Human");
        System.out.println("2. Random Player");
        System.out.print("Select [1-2]: ");

        int choiceTwo = console.nextInt();
        console.nextLine();

        Player thePlayer;
        if (choiceTwo == 1) {
            System.out.print("Enter your name: ");
            String name = console.nextLine();
            thePlayer = new HumanPlayer(name);
            System.out.println("\nHello " + thePlayer.getName() + "\n");

        } else {
            thePlayer = new RandomPlayer();
            System.out.println("\n" + thePlayer.getName() + "\n");

        }
        return thePlayer;
    }


}

