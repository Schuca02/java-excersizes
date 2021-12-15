import learn.BoardGame;
import learn.GameRepository;

import java.util.ArrayList;

public class Exercise04 {

    public static void main(String[] args) {

        ArrayList<BoardGame> games = GameRepository.getAll();
        ArrayList<BoardGame> videoGames = new ArrayList<>(3);
        videoGames.add(new BoardGame("Spider-man", 1, 2, "Retro"));
        videoGames.add(new BoardGame("Battlefield", 1, 124, "MMO Warefare"));
        videoGames.add(new BoardGame("Battle Toads", 1, 2, "Retro Platform"));

        games.addAll(videoGames);

        Exercise02.printAll(games);
        // 1. Instantiate a new ArrayList<BoardGame>.
        // 2. Add three BoardGames to the new list.
        // 3. Print the new list.
        // 4. Add items in the new list to `games` with the `addAll` method.
        // 5. Print `games`.


    }
}
