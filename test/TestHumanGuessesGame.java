import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

public class TestHumanGuessesGame {
    public static class MockRandom extends Random {
        public MockRandom() {
            super();
        }

        @Override
        public int nextInt(int bound) {
            if (bound <= 0) {
                throw new IllegalArgumentException();
            }
            return (int)(.5 * bound);
        }
    }

    //using dependency injection
    @Test
    public void testMakeGuess() {
        HumanGuessesGame hgg = new HumanGuessesGame(new MockRandom());
        // target is set to be a random int [0, UPPER_BOUND) + 1 and UPPER_BOUND = 1000,
        // target = 501 since the MockRandom.nextInt(int bound) returns (bound * .5)
        assertEquals(GuessResult.LOW, hgg.makeGuess(100));
        assertEquals(GuessResult.LOW, hgg.makeGuess(1));
        assertEquals(GuessResult.LOW, hgg.makeGuess(500));
        assertEquals(GuessResult.HIGH, hgg.makeGuess(900));
        assertEquals(GuessResult.HIGH, hgg.makeGuess(1000));
        assertEquals(GuessResult.HIGH, hgg.makeGuess(502));
        assertEquals(GuessResult.CORRECT, hgg.makeGuess(501));
    }

    @Test
    public void testGetNumGuesses() {
        HumanGuessesGame hgg = new HumanGuessesGame(new MockRandom());

        assertEquals(0, hgg.getNumGuesses());
        hgg.makeGuess(10);
        hgg.makeGuess(20);
        hgg.makeGuess(30);
        assertEquals(3, hgg.getNumGuesses());
        hgg.makeGuess(40);
        hgg.makeGuess(50);
        assertEquals(5, hgg.getNumGuesses());
        hgg.makeGuess(501);
        assertEquals(6, hgg.getNumGuesses());
    }

    @Test
    public void testIsDone() {
        HumanGuessesGame hgg = new HumanGuessesGame(new MockRandom());

        assertFalse(hgg.isDone());
        hgg.makeGuess(10);
        assertFalse(hgg.isDone());
        hgg.makeGuess(501);
        assertTrue(hgg.isDone()); // this is a bug: isDone is never updated
    }
}
