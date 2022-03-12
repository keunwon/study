package numberguessing.console;

import numberguessing.PositiveIntegerGenerator;

import java.util.stream.Stream;

public class AppModel {
    private final String SELECT_MODE_MESSAGE = """
            1: Single player game
            2: Multiplayer game
            3: Exit
            Enter selection:\040""";

    interface Processor {
        Processor run(String input);
    }

    private final TextOutput output;
    private final PositiveIntegerGenerator generator;
    private boolean completed;
    private Processor processor;

    public AppModel(PositiveIntegerGenerator generator) {
        output = new TextOutput(SELECT_MODE_MESSAGE);
        this.generator = generator;
        completed = false;
        this.processor = this::processModeSelection;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void processInput(String input) {
        processor = processor.run(input);
    }

    public String flushOutput() {
        return output.flushOutput();
    }

    private Processor processModeSelection(String input) {
        if ("1".equals(input)) {
            printLines("Single player game", "I'm thinking of a number between 1 and 100.", "Enter your guess: ");
            final var answer = generator.generateLessThanOrEqualToHundred();
            return getSinglePlayerGameProcessor(answer, 1);
        } else if ("2".equals(input)) {
            printLines("Multiplayer game", "Enter player names separated with commas: ");

            return startMultiPlayerGameProcessor();
        } else {
            completed = true;
        }
        return null;
    }

    private Processor startMultiPlayerGameProcessor() {
        return input -> {
            printLines("I'm thinking of a number between 1 and 100.", "");

            final var players = Stream.of(input.split(","))
                    .map(String::trim)
                    .toArray();
            final var answer = generator.generateLessThanOrEqualToHundred();
            return getMultiPlayerGameProcessor(players, answer, 1);
        };
    }

    private Processor getMultiPlayerGameProcessor(Object[] players, int answer, int tries) {
        final Object player = players[(tries - 1) % players.length];

        printLines("Enter %s's guess: ".formatted(player));
        return input -> {
            final var guess = Integer.parseInt(input);

            if (guess < answer) {
                printLines("%s's guess is too low.".formatted(player), "");
            } else if (guess > answer) {
                printLines("%s's guess is too high.".formatted(player), "");
            } else {
                printLines("Correct! %s wins.".formatted(player), SELECT_MODE_MESSAGE);
                return this::processModeSelection;
            }
            return getMultiPlayerGameProcessor(players, answer, tries + 1);
        };
    }

    private Processor getSinglePlayerGameProcessor(int answer, int tries) {
        return input -> {
            final var guess = Integer.parseInt(input);
            if (guess < answer) {
                printLines("Your guess is too low.", "Enter your guess: ");
                return getSinglePlayerGameProcessor(answer, tries + 1);
            } else if (guess > answer) {
                printLines("Your guess is too high.", "Enter your guess: ");
                return getSinglePlayerGameProcessor(answer, tries + 1);
            } else {
                printLines("Correct! %d %s.".formatted(tries, tries == 1 ? "guess" : "guesses"), SELECT_MODE_MESSAGE);
                return this::processModeSelection;
            }
        };
    }

    private void printLines(String... lines) {
        output.printLines(lines);
    }
}
