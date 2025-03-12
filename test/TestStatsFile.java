import org.junit.jupiter.api.Test;

import java.util.SortedMap;
import java.util.TreeMap;
import static org.junit.jupiter.api.Assertions.*;

//using dependency injection
public class TestStatsFile {

    public static class MockStatsFile extends StatsFile {

        public MockStatsFile() {
            this.statsMap = new TreeMap<>();
        }

        public void setNumGames(int numGuesses, int numGamesWithThatCount){
            this.statsMap.put(numGuesses,numGamesWithThatCount);
        }
    }

    @Test
    public void TestNumGamesWithMultipleGames(){
        MockStatsFile mock = new MockStatsFile();
        mock.setNumGames(7,10);
        mock.setNumGames(10,3);
        mock.setNumGames(15,4);

        assertEquals(10,mock.numGames(7));
        assertEquals(3,mock.numGames(10));
        assertEquals(4,mock.numGames(15));
    }

    @Test
    public void TestNumGamesWithEmptyMap(){
        MockStatsFile mock = new MockStatsFile();
        assertEquals(0,mock.numGames(7));
        assertEquals(0,mock.numGames(10));
        assertEquals(0,mock.numGames(15));
    }

    @Test
    public void TestNumGamesWhenThereAreNoGamesWithThatNumberOfGuesses(){
        MockStatsFile mock = new MockStatsFile();
        assertEquals(0,mock.numGames(10));
        assertEquals(0,mock.numGames(1000));
        assertEquals(0,mock.numGames(0));
    }

    @Test
    public void TestNumGamesWithNegativeGuesses(){
        MockStatsFile mock = new MockStatsFile();
        assertEquals(0,mock.numGames(-10));
        assertEquals(0,mock.numGames(-1000));
    }

    @Test
    public void TestMaxNumGuessesWithMultipleGames(){
        MockStatsFile mock = new MockStatsFile();

        mock.setNumGames(7,10);
        mock.setNumGames(10,3);
        mock.setNumGames(15,4);

        assertEquals(15,mock.maxNumGuesses());
    }

    @Test
    public void TestMaxNumGuessesWithEmptyTree(){
        MockStatsFile mock = new MockStatsFile();
        assertEquals(0,mock.maxNumGuesses());
    }

    @Test
    public void TestMaxNumGuessesWithNegativeGuesses(){
        MockStatsFile mock = new MockStatsFile();

        mock.setNumGames(-7,10);
        mock.setNumGames(-10,3);
        mock.setNumGames(-15,4);

        assertEquals(-7,mock.maxNumGuesses());
    }






}
