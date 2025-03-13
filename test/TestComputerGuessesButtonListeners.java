import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestComputerGuessesButtonListeners {
    @Test
    public void testGetNumGuesses() {
        ComputerGuessesButtonListeners c = new ComputerGuessesButtonListeners();
        c.onLowerButtonPressed();
        c.onHigherButtonPressed();
        c.onEqualButtonPressed();

        assertEquals(3, c.getNumGuesses()); // this is a bug. it should return 3, but actually returns 3
    }

    @Test
    public void testCalcGuess() {
        ComputerGuessesButtonListeners c = new ComputerGuessesButtonListeners();

        assertEquals(501, c.calcGuess());
        c.onLowerButtonPressed();
        assertEquals(251, c.calcGuess());
    }

    @Test
    public void testFindUpperBound() {
        int upperBound = 10, lastGuess = 5;
        assertEquals(4, ComputerGuessesButtonListeners.findUpperBound(upperBound, lastGuess));
        // this is a bug, the upper bound should be min(upperBound, lastGuess - 1), not min(upperBound, lastGuess)

        upperBound = 5;
        lastGuess = 10;
        assertEquals(5, ComputerGuessesButtonListeners.findUpperBound(upperBound, lastGuess));
    }

    @Test
    public void testFindLowerBound() {
        int lowerBound = 5, lastGuess = 10;
        assertEquals(11, ComputerGuessesButtonListeners.findLowerBound(lowerBound, lastGuess));

        lowerBound = 10;
        lastGuess = 5;
        assertEquals(10, ComputerGuessesButtonListeners.findLowerBound(lowerBound, lastGuess));
    }

    @Test
    public void testLastGuess() {
        assertEquals(5, ComputerGuessesButtonListeners.lastGuess(10, 0));
        assertEquals(6, ComputerGuessesButtonListeners.lastGuess(10, 1));
        assertEquals(5, ComputerGuessesButtonListeners.lastGuess(5, 5));
    }
}
