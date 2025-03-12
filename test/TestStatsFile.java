import org.junit.jupiter.api.Test;

import java.util.SortedMap;
import java.util.TreeMap;

//using dependency injection



public class TestStatsFile {

    public static class MockStatsFile extends StatsFile {

        public MockStatsFile() {
            statsMap = new TreeMap<>();
        }

        @Override
        public int numGames(int numGuesses) {
            return 1;
        }

        @Override
        public int maxNumGuesses(){
            return (statsMap.isEmpty() ? 0 : statsMap.lastKey());
        }
    }

    @Test
    public void TestNumGames(){

    }

    @Test
    public void TestMaxNumGuesses(){

    }




}
