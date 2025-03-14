import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.SortedMap;
import java.util.TreeMap;
import static org.junit.jupiter.api.Assertions.*;
import java.time.format.DateTimeParseException;


public class TestStatsFile {

    public static class MockStatsFile extends StatsFile {
        String [] values = null;

        public MockStatsFile() {
            this.statsMap = new TreeMap<>();
            this.values = new String[]{"2025-03-12T14:30:00", "5"};
        }

        public void setNumGames(int numGuesses, int numGamesWithThatCount){
            this.statsMap.put(numGuesses,numGamesWithThatCount);
        }
    }

    //using dependency injection
    @Test
    public void testNumGamesWithMultipleGames(){
        MockStatsFile mock = new MockStatsFile();
        mock.setNumGames(7,10);
        mock.setNumGames(10,3);
        mock.setNumGames(15,4);

        assertEquals(10,mock.numGames(7));
        assertEquals(3,mock.numGames(10));
        assertEquals(4,mock.numGames(15));
    }

    @Test
    public void testNumGamesWithEmptyMap(){
        MockStatsFile mock = new MockStatsFile();
        assertEquals(0,mock.numGames(7));
        assertEquals(0,mock.numGames(10));
        assertEquals(0,mock.numGames(15));
    }

    @Test
    public void testNumGamesWhenThereAreNoGamesWithThatNumberOfGuesses(){
        MockStatsFile mock = new MockStatsFile();
        assertEquals(0,mock.numGames(10));
        assertEquals(0,mock.numGames(1000));
        assertEquals(0,mock.numGames(0));
    }

    @Test
    public void testNumGamesWithNegativeGuesses(){
        MockStatsFile mock = new MockStatsFile();
        assertEquals(0,mock.numGames(-10));
        assertEquals(0,mock.numGames(-1000));
    }

    @Test
    public void testMaxNumGuessesWithMultipleGames(){
        MockStatsFile mock = new MockStatsFile();

        mock.setNumGames(7,10);
        mock.setNumGames(10,3);
        mock.setNumGames(15,4);

        assertEquals(15,mock.maxNumGuesses());
    }

    @Test
    public void testMaxNumGuessesWithEmptyTree(){
        MockStatsFile mock = new MockStatsFile();
        assertEquals(0,mock.maxNumGuesses());
    }

    @Test
    public void testMaxNumGuessesWithNegativeGuesses(){
        MockStatsFile mock = new MockStatsFile();

        mock.setNumGames(-7,10);
        mock.setNumGames(-10,3);
        mock.setNumGames(-15,4);

        assertEquals(-7,mock.maxNumGuesses());
    }

    @Test
    public void testParsedValues(){
        MockStatsFile mock = new MockStatsFile();

        Pair<LocalDateTime,Integer> testParse = mock.parseDateAndGuesses(mock.values);
        LocalDateTime expectedDateTime = LocalDateTime.of(2025, 3, 12, 14, 30, 0);
        int expectedGuesses = 5;

        assertEquals(expectedGuesses,testParse.getRight());
        assertEquals(expectedDateTime,testParse.getLeft());
    }

    @Test
    public void testParsedValuesToThrowErrorOnNonNumeric(){
        MockStatsFile mock = new MockStatsFile();
        mock.values = new String[]{"2025-03-12T14:30:00", "five"};


        assertThrows(NumberFormatException.class, () -> {
            mock.parseDateAndGuesses(mock.values);
        });
        }

    @Test
    public void testParsedValuesIncorrectDateFormat(){
        MockStatsFile mock = new MockStatsFile();
        mock.values = new String[]{"202-03-12T14:30", "5"};

        assertThrows(DateTimeParseException.class, () -> {
            mock.parseDateAndGuesses(mock.values);
        });
    }


    @Test
    public void TestSumGames(){
        MockStatsFile mock = new MockStatsFile();
        mock.setNumGames(7,10);
        mock.setNumGames(10,3);
        mock.setNumGames(15,4);

        assertEquals(0,mock.sumGames(1,1,mock));
        assertEquals(0,mock.sumGames(2,4,mock));
        assertEquals(17,mock.sumGames(5,15,mock));
    }
}





