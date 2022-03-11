package numberguessing.console;

import numberguessing.PositiveIntegerGenerator;

public class AppModel {
    private int answer;
    private boolean completed;
    private boolean singlePlayerMode;
    private String output;
    private int tries;

    public AppModel(PositiveIntegerGenerator generator) {
        answer = generator.generateLessThanOrEqualToHundred();
        completed = false;
        singlePlayerMode = false;
        output = """
                1: Single player game
                2: Multiplayer game
                3: Exit
                Enter selection:\040""";
        tries = 0;
    }

    public boolean isCompleted() {
        return completed;
    }

    public String flushOutput() {
        return output;
    }

    public void processInput(String input) {
        if (singlePlayerMode) {
            processSinglePlayerGame(input);
        } else {
            processModeSelection(input);
        }
    }

    private void processSinglePlayerGame(String input) {
        tries++;
        var guess = Integer.parseInt(input);
        if (guess < answer) {
            output = """
                Your guess is too high.
                Enter your guess:\040""";
        } else if (guess > answer) {
            output = """
                Your guess is too low.
                Enter your guess:\040""";
        } else {
            output = "Correct! %d guesses.%s".formatted(tries, System.lineSeparator());
        }
    }

    private void processModeSelection(String input) {
        if ("1".equals(input)) {
            output = """
                    Single player game
                    I'm thinking of a number between 1 and 100.
                    Enter your guess:\040""";
            singlePlayerMode = true;
        } else {
            completed = true;
        }
    }
}
