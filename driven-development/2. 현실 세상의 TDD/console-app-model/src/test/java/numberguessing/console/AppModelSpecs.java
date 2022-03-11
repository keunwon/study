package numberguessing.console;

import numberguessing.PositiveIntegerGeneratorStub;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AppModelSpecs {
    private static final String NEW_LINE = System.lineSeparator();

    @Test
    void sut_is_incompleted_when_it_is_initialized() {
        final var sut = new AppModel(new PositiveIntegerGeneratorStub(50));
        boolean actual = sut.isCompleted();

        assertFalse(actual);
    }

    @Test
    void sut_correctly_prints_select_mode_message() {
        final var sut = new AppModel(new PositiveIntegerGeneratorStub(50));
        final var actual = sut.flushOutput();

        assertEquals("1: Single player game" + NEW_LINE + "2: Multiplayer game" + NEW_LINE + "3: Exit"
                + NEW_LINE + "Enter selection: ", actual);
    }

    @Test
    void sut_correctly_exits() {
        final var sut = new AppModel(new PositiveIntegerGeneratorStub(50));

        sut.processInput("3");

        final var actual = sut.isCompleted();
        assertTrue(actual);
    }

    @Test
    void set_correctly_prints_single_player_game_start_message() {
        final var sut = new AppModel(new PositiveIntegerGeneratorStub(50));
        sut.flushOutput();
        sut.processInput("1");

        final var actual = sut.flushOutput();

        assertEquals("Single player game" + NEW_LINE + "I'm thinking of a number between 1 and 100."
                + NEW_LINE + "Enter your guess: ", actual);
    }

    @ParameterizedTest
    @CsvSource({"50, 40", "30, 29", "89, 9"})
    void sut_correctly_prints_too_low_message_in_single_player_game(int answer, int guess) {
        final var sut = new AppModel(new PositiveIntegerGeneratorStub(answer));
        sut.processInput("1");
        sut.flushOutput();
        sut.processInput(Integer.toString(guess));

        final var actual = sut.flushOutput();

        assertEquals("Your guess is too high." + NEW_LINE + "Enter your guess: ", actual);
    }

    @ParameterizedTest
    @CsvSource({"50, 60", "80, 81"})
    void sut_correctly_prints_too_high_message_in_single_player_game(int answer, int guess) {
        final var sut = new AppModel(new PositiveIntegerGeneratorStub(answer));
        sut.processInput("1");
        sut.flushOutput();
        sut.processInput(Integer.toString(guess));

        final var actual = sut.flushOutput();

        assertEquals("Your guess is too low." + NEW_LINE + "Enter your guess: ", actual);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 10, 100})
    void sut_correctly_prints_correct_message_in_single_player_game(int answer) {
        final var sut = new AppModel(new PositiveIntegerGeneratorStub(answer));
        sut.processInput("1");
        sut.flushOutput();

        sut.processInput(Integer.toString(answer));

        final var actual = sut.flushOutput();
        assertThat(actual).startsWith("Correct! ");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 10, 100})
    void sut_correctly_prints_guess_count_if_single_player_game_finished(int fails) {
        final var sut = new AppModel(new PositiveIntegerGeneratorStub(50));
        sut.processInput("1");

        for (int i = 0; i < fails; i++) {
            sut.processInput("30");
        }
        sut.flushOutput();
        sut.processInput("50");

        final var actual = sut.flushOutput();
        assertThat(actual).contains((fails + 1) + " guesses." + NEW_LINE);
    }
}