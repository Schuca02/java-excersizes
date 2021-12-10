import learn.BoardGame;
import learn.GameRepository;

import java.util.ArrayList;

public class Exercise07 {

    public static void main(String[] args) {

        ArrayList<BoardGame> games = GameRepository.getAll();
        ArrayList<BoardGame> economicGames = new ArrayList<>();
        for (BoardGame game : games) {
            if (game.getCategory() == "Economic Game") {
                economicGames.add(game);
            }
        }
        Exercise02.printAll(economicGames);
        // 1. Instantiate a new ArrayList<BoardGame> and call it `economicGames`.
        // 2. Loop over `games`. Add each game with the "Economic" category to `economicGames`.
        // 3. Print `economicGames`.
    }
}
