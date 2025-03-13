public class ComputerGuessesButtonListeners {
    private int numGuesses;
    private int lastGuess;

    // upperBound and lowerBound track the computer's knowledge about the correct number
    // They are updated after each guess is made
    private int upperBound; // correct number is <= upperBound
    private int lowerBound; // correct number is >= lowerBound

    public ComputerGuessesButtonListeners(){
        reset();
    }

    public void reset() {
        this.numGuesses = 0;
        this.upperBound = 1000;
        this.lowerBound = 1;
    }

    public int getLastGuess(){
        return this.lastGuess;
    }

    public int getNumGuesses(){
        return this.numGuesses;
    }

    public int calcGuess(){
        this.lastGuess = (this.lowerBound + this.upperBound + 1) / 2;
        return this.lastGuess;
    }

    public void onLowerButtonPressed(){
        this.upperBound = findUpperBound(this.upperBound,this.lastGuess);
        this.lastGuess = lastGuess(this.upperBound,this.lowerBound);
        this.numGuesses += 1;
    }

    public void onHigherButtonPressed(){
        this.lowerBound = findLowerBound(this.lowerBound,this.lastGuess);
        this.lastGuess = lastGuess(this.upperBound,this.lowerBound);
        this.numGuesses += 1;
    }

    public GameResult onEqualButtonPressed() {
        return new GameResult(false, getLastGuess(), getNumGuesses());
    }

    public static int findUpperBound(int upperBound, int lastGuess){
        return Math.min(upperBound,lastGuess);
    }

    public static int findLowerBound(int lowerBound, int lastGuess){
        return Math.max(lowerBound, lastGuess + 1);
    }

    public static int lastGuess(int upperBound, int lowerBound){
        return (lowerBound + upperBound + 1) / 2;
    }
}
