# COMP 452 Code Testing Project

### Authors
[Austin Hensley](https://github.com/hensleyas21),
[Ethan Kesterholt](https://github.com/Kesterholtem21)

### Date
March 13, 2025

## Bugs Found
- `isDone` is never updated
- the number of guesses the computer makes is off by one (i.e., guessing right on the first try displays 0 guesses)
- the lower button in ComputerGuessesPanel should set the `upperBound = Math.min(upperBound, lastGuess - 1)`, but is currently setting it to `upperBound = Math.min(upperBound, lastGuess)`

## Logical Errors
- Giving the computer false information (i.e., pressing the higher button when the actual number is lower) will cause the computer to get stuck and never guess the correct number
