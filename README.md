# COMP 452 Code Testing Project

Authors: Austin Hensley and Ethan Kesterholt
Date: March 13, 2025

## Bugs found
- isDone is never updated
- the number of guesses the computer makes is off by one (i.e, guessing right on the first try displays 0 guesses)
- the lower button in ComputerGuessesPanel should set the upperBound = Math.min(upperBound, lastGuess - 1), but is currently setting it to Math.min(upperBound, lastGuess)