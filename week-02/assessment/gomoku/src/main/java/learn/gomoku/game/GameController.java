package learn.gomoku.game;

import java.util.Scanner;

import learn.gomoku.players.Player;
import learn.gomoku.players.HumanPlayer;
import learn.gomoku.players.RandomPlayer;


public class GameController {

    public void run() {

        Scanner console = new Scanner(System.in);

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

