import learn.BoardGame;
import learn.GameRepository;

import java.util.ArrayList;

public class Exercise03 {

    public static void main(String[] args) {

        ArrayList<BoardGame> games = GameRepository.getAll();

        games.add(new BoardGame("Trails", 2, 8, "Cooperative"));
        Exercise02.printAll(games);
        games.add(new BoardGame("Risk", 2, 6, "Builds Hate"));
        Exercise02.printAll(games);
        games.add(new BoardGame("Mouse Trap", 1, 4, "Child's Play"));
        Exercise02.printAll(games);

        // 1. Add three new games to `games` with the `add` method.
        // 2. Print `games` after each add.
    }
}
