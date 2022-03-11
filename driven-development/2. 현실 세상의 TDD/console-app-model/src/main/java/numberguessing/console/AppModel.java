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

    private final PositiveIntegerGenerator generator;
    private final StringBuffer outputBuffer;
    private boolean completed;
    private Processor processor;

    public AppModel(PositiveIntegerGenerator generator) {
        this.generator = generator;
        outputBuffer = new StringBuffer(SELECT_MODE_MESSAGE);
        completed = false;
        this.processor = this::processModeSelection;
    }

    public boolean isCompleted() {
        return completed;
    }

    public String flushOutput() {
        final var output = outputBuffer.toString();
        outputBuffer.setLength(0);
        return output;
    }

    public void processInput(String input) {
        processor = processor.run(input);
    }

    private Processor processModeSelection(String input) {
        if ("1".equals(input)) {
            println("Single player game");
            println("I'm thinking of a number between 1 and 100.");
            print("Enter your guess: ");

            final var answer = generator.generateLessThanOrEqualToHundred();
            return getSinglePlayerGameProcessor(answer, 1);
        } else if ("2".equals(input)) {
            println("Multiplayer game");
            print("Enter player names separated with commas: ");

            return startMultiPlayerGameProcessor();
        } else {
            completed = true;
        }
        return null;
    }

    private Processor startMultiPlayerGameProcessor() {
        return input -> {
            println("I'm thinking of a number between 1 and 100.");

            final var players = Stream.of(input.split(","))
                    .map(String::trim)
                    .toArray();
            final var answer = generator.generateLessThanOrEqualToHundred();
            return getMultiPlayerGameProcessor(players, answer, 1);
        };
    }

    private Processor getMultiPlayerGameProcessor(Object[] players, int answer, int tries) {
        final Object player = players[(tries - 1) % players.length];

        print("Enter %s's guess: ".formatted(player));
        return input -> {
            final var guess = Integer.parseInt(input);

            if (guess < answer) {
                println("%s's guess is too low.".formatted(player));
            } else if (guess > answer) {
                println("%s's guess is too high.".formatted(player));
            } else {
                println("Correct! %s wins.".formatted(player));
                print(SELECT_MODE_MESSAGE);
                return this::processModeSelection;
            }
            return getMultiPlayerGameProcessor(players, answer, tries + 1);
        };
    }

    private Processor getSinglePlayerGameProcessor(int answer, int tries) {
        return input -> {
            final var guess = Integer.parseInt(input);
            if (guess < answer) {
                println("Your guess is too low.");
                print("Enter your guess: ");
                return getSinglePlayerGameProcessor(answer, tries + 1);
            } else if (guess > answer) {
                println("Your guess is too high.");
                print("Enter your guess: ");
                return getSinglePlayerGameProcessor(answer, tries + 1);
            } else {
                println("Correct! %d %s.".formatted(tries, tries == 1 ? "guess" : "guesses"));
                print(SELECT_MODE_MESSAGE);
                return this::processModeSelection;
            }
        };
    }

    private void print(String message) {
        outputBuffer.append(message);
    }

    private void println(String line) {
        outputBuffer.append(line);
        outputBuffer.append(System.lineSeparator());
    }
}
