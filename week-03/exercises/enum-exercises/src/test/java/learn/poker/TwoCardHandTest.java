package learn.poker;

import learn.cards.Card;
import learn.cards.Rank;
import learn.cards.Suits;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TwoCardHandTest {

    // 1. Instantiate hands with the appropriate suit and rank for each test.
    // 2. Run the tests and confirm they pass. Do NOT edit existing tests.
    // 3. Add a couple more tests to confirm everything is working as intended.

    @Test
    void twoQueensShouldBeatTwo10s() {
        // TODO: instantiate Cards and TwoCardHands with appropriate arguments
        TwoCardHand queenQueen = new TwoCardHand(new Card(Rank.QUEEN, Suits.HEARTS), new Card(Rank.QUEEN, Suits.CLUBS));
        TwoCardHand tenTen = new TwoCardHand(new Card(Rank.TEN, Suits.DIAMONDS), new Card(Rank.TEN, Suits.HEARTS));
        assertTrue(queenQueen.compareTo(tenTen) > 0);
    }

    @Test
    void two9sShouldBeatJack10() {
        // TODO: instantiate Cards and TwoCardHands with appropriate arguments
        TwoCardHand nineNine = new TwoCardHand(new Card(Rank.NINE, Suits.HEARTS), new Card(Rank.NINE, Suits.CLUBS));
        TwoCardHand jackTen = new TwoCardHand(new Card(Rank.JACK, Suits.CLUBS), new Card(Rank.TEN, Suits.CLUBS));
        assertTrue(nineNine.compareTo(jackTen) > 0);
    }

    @Test
    void queen4ShouldBeatJack10() {
        // TODO: instantiate Cards and TwoCardHands with appropriate arguments
        TwoCardHand queenFour = new TwoCardHand(new Card(Rank.QUEEN, Suits.DIAMONDS), new Card(Rank.FOUR, Suits.SPADES));
        TwoCardHand jackTen = new TwoCardHand(new Card(Rank.JACK, Suits.HEARTS), new Card(Rank.TEN, Suits.DIAMONDS));
        assertTrue(queenFour.compareTo(jackTen) > 0);
    }

    @Test
    void queen5ShouldBeatQueen3() {
        // TODO: instantiate Cards and TwoCardHands with appropriate arguments
        TwoCardHand queenFive = new TwoCardHand(new Card(Rank.QUEEN, Suits.SPADES), new Card(Rank.FIVE, Suits.DIAMONDS));
        TwoCardHand queenThree = new TwoCardHand(new Card(Rank.QUEEN, Suits.DIAMONDS), new Card(Rank.THREE, Suits.CLUBS));
        assertTrue(queenFive.compareTo(queenThree) > 0);
    }

    @Test
    void two5sShouldTieTwo5s() {
        // TODO: instantiate Cards and TwoCardHands with appropriate arguments
        TwoCardHand firstFiveFive = new TwoCardHand(new Card(Rank.FIVE, Suits.CLUBS), new Card(Rank.FIVE, Suits.HEARTS));
        TwoCardHand secondFiveFive = new TwoCardHand(new Card(Rank.FIVE, Suits.DIAMONDS), new Card(Rank.FIVE, Suits.SPADES));
        assertEquals(0, firstFiveFive.compareTo(secondFiveFive));
    }

    @Test
    void jack4ShouldTieJack4() {
        // TODO: instantiate Cards and TwoCardHands with appropriate arguments
        TwoCardHand firstJackFour = new TwoCardHand(new Card(Rank.JACK, Suits.SPADES), new Card(Rank.FOUR, Suits.HEARTS));
        TwoCardHand secondJackFour = new TwoCardHand(new Card(Rank.JACK, Suits.DIAMONDS), new Card(Rank.FOUR, Suits.CLUBS));
        assertEquals(0, firstJackFour.compareTo(secondJackFour));
    }
    @Test
    void QueenKingShouldBeatJackKing() {
        // TODO: instantiate Cards and TwoCardHands with appropriate arguments
        TwoCardHand firstQueenKing = new TwoCardHand(new Card(Rank.QUEEN, Suits.SPADES), new Card(Rank.KING, Suits.HEARTS));
        TwoCardHand secondJackKing = new TwoCardHand(new Card(Rank.JACK, Suits.DIAMONDS), new Card(Rank.KING, Suits.CLUBS));
        assertEquals(1, firstQueenKing.compareTo(secondJackKing));
    }
}