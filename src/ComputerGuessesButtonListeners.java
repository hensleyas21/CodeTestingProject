public class ComputerGuessesButtonListeners {



public static int findUpperBound(int upperBound, int lastGuess){
    return Math.min(upperBound,lastGuess);
}

public static int findLowerBound(int lowerBound, int lastGuess){
    return Math.max(lowerBound, lastGuess + 1);
}


public static int LastGuess(int upperBound, int lowerBound){
    return (lowerBound + upperBound + 1) / 2;
}
}
