package learn.gomoku;
import learn.gomoku.game.GameController;

public class App {

    public static void main(String[] args) {
        GameController gameController = new GameController();
        gameController.run();
    }
}
