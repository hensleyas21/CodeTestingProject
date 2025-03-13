import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

/**
 * UI screen for when the computer is guessing a number
 *
 * Displays the computer's guesses and processes human's answers
 * Tracks the computer's guesses
 *
 * TODO: refactor this class
 */
public class ComputerGuessesPanel extends JPanel {

//    private int numGuesses;
//    private int lastGuess;
//
//    // upperBound and lowerBound track the computer's knowledge about the correct number
//    // They are updated after each guess is made
//    private int upperBound; // correct number is <= upperBound
//    private int lowerBound; // correct number is >= lowerBound

    private ComputerGuessesButtonListeners backend;

    public ComputerGuessesPanel(JPanel cardsPanel, Consumer<GameResult> gameFinishedCallback){
        backend = new ComputerGuessesButtonListeners();

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        //not sure if maybe text adjustment needs its own method
        JLabel guessMessage = guessMessage();

        this.add(Box.createRigidArea(new Dimension(0, 40)));

        //same thing here, maybe consider moving it all to its own method
        prompt();

        //definitely its own method, especially for the math
        lowerButton(guessMessage);

        //same as lower
        correctButton(cardsPanel, gameFinishedCallback, guessMessage);

        //same as lower and equal
        higherButton(guessMessage);

        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent e) {

                int lastGuess = backend.calcLastGuess();
                guessMessage.setText("I guess " + lastGuess + ".");
            }
        });
    }

    private JLabel guessMessage() {
        JLabel guessMessage = new JLabel("I guess ___.");
        guessMessage.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(guessMessage);
        guessMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
        return guessMessage;
    }

    private void prompt() {
        JLabel prompt = new JLabel("Your number is...");
        this.add(prompt);
        prompt.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(0,10)));
    }

    private void lowerButton(JLabel guessMessage) {
        JButton lowerBtn = new JButton("Lower");
        lowerBtn.addActionListener(e -> {
            //this is a bug, needs a -1 for upper bound on lastGuess
            //upperBound = Math.min(upperBound, lastGuess);
            backend.onLowerButtonPressed();
            guessMessage.setText("I guess " + backend.getLastGuess() + ".");
        });
        this.add(lowerBtn);
        lowerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(0,10)));
    }

    private void correctButton(JPanel cardsPanel, Consumer<GameResult> gameFinishedCallback, JLabel guessMessage) {
        JButton correctBtn = new JButton("Equal");
        correctBtn.addActionListener(e -> {
            guessMessage.setText("I guess ___.");

            // Send the result of the finished game to the callback
            GameResult result = new GameResult(false, backend.getLastGuess(), backend.getNumGuesses());
            gameFinishedCallback.accept(result);

            CardLayout cardLayout = (CardLayout) cardsPanel.getLayout();
            cardLayout.show(cardsPanel, ScreenID.GAME_OVER.name());
        });
        this.add(correctBtn);
        correctBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
    }

    private void higherButton(JLabel guessMessage) {
        JButton higherBtn = new JButton("Higher");
        higherBtn.addActionListener(e -> {

            backend.onHigherButtonPressed();
            guessMessage.setText("I guess " + backend.getLastGuess() + ".");
        });
        this.add(higherBtn);
        higherBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
    }
}
