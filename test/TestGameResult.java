import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestGameResult {
    @Test
    public void testGameResultConstructor() {
        GameResult gr1 = new GameResult(true, 10, 5);
        assertTrue(gr1.humanWasPlaying);
        assertEquals(10, gr1.correctValue);
        assertEquals(5, gr1.numGuesses);

        GameResult gr2 = new GameResult(false, 7, 3);
        assertFalse(gr2.humanWasPlaying);
        assertEquals(7, gr2.correctValue);
        assertEquals(3, gr2.numGuesses);
    }
}
