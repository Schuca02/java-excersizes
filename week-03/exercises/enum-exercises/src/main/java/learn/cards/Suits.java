package learn.cards;

public enum Suits {
    SPADES("Spades"),
    HEARTS("Hearts"),
    CLUBS("Clubs"),
    DIAMONDS("Diamonds");

    private final String name;


    Suits(String name) {
        this.name = name;
    }
}