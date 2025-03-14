import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.commons.lang3.tuple.Pair;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * File-backed implementation of GameStats
 *
 * Returns the number of games *within the last 30 days* where the person took a given number of guesses
 */
public class StatsFile extends GameStats {
    public static final String FILENAME = "guess-the-number-stats.csv";


    // maps the number of guesses required to the number of games within
    // the past 30 days where the person took that many guesses
    protected SortedMap<Integer, Integer> statsMap;

    public StatsFile(){
        statsMap = new TreeMap<>();
        LocalDateTime limit = LocalDateTime.now().minusDays(30);

        try (CSVReader csvReader = new CSVReader(new FileReader(FILENAME))) {
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                // values should have the date and the number of guesses as the two fields
                try {
                    Pair<LocalDateTime, Integer> parsedValues = parseDateAndGuesses(values);
                    int numGuesses = parsedValues.getRight();
                    addStat(limit, parsedValues.getLeft(),numGuesses);
                }
                catch(NumberFormatException nfe){
                    // NOTE: In a full implementation, we would log this error and possibly alert the user
                    throw nfe;
                }
                catch(DateTimeParseException dtpe){
                    // NOTE: In a full implementation, we would log this error and possibly alert the user
                    throw dtpe;
                }
            }
        } catch (CsvValidationException e) {
            // NOTE: In a full implementation, we would log this error and alert the user
            // NOTE: For this project, you do not need unit tests for handling this exception.
        } catch (IOException e) {
            // NOTE: In a full implementation, we would log this error and alert the user
            // NOTE: For this project, you do not need unit tests for handling this exception.
        }
    }


    public Pair<LocalDateTime, Integer> parseDateAndGuesses(String[] values)throws NumberFormatException, DateTimeParseException{
        LocalDateTime timestamp = LocalDateTime.parse(values[0]);
        Integer numGuesses = Integer.parseInt(values[1]);
        return Pair.of(timestamp,numGuesses);
    }

    public void addStat(LocalDateTime limit, LocalDateTime timestamp, int numGuesses){
        if (timestamp.isAfter(limit)) {
            statsMap.put(numGuesses, 1 + statsMap.getOrDefault(numGuesses, 0));
        }
    }

    public int sumGames(int lowerBound, int upperBound, GameStats stats){
        int numGames = 0;
        for(int numGuesses=lowerBound; numGuesses <= upperBound; numGuesses++) {
            numGames += stats.numGames(numGuesses);
        }

        return  numGames;
    }

    @Override
    public int numGames(int numGuesses) {
        return statsMap.getOrDefault(numGuesses, 0);
    }

    @Override
    public int maxNumGuesses(){
        return (statsMap.isEmpty() ? 0 : statsMap.lastKey());
    }
}
