package numberguessing.console;

import numberguessing.PositiveIntegerGeneratorStub;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.powermock.reflect.Whitebox;

import java.util.List;
import java.util.stream.Stream;

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

        assertEquals("Your guess is too low." + NEW_LINE + "Enter your guess: ", actual);
    }

    @ParameterizedTest
    @CsvSource({"50, 60", "80, 81"})
    void sut_correctly_prints_too_high_message_in_single_player_game(int answer, int guess) {
        final var sut = new AppModel(new PositiveIntegerGeneratorStub(answer));
        sut.processInput("1");
        sut.flushOutput();
        sut.processInput(Integer.toString(guess));

        final var actual = sut.flushOutput();

        assertEquals("Your guess is too high." + NEW_LINE + "Enter your guess: ", actual);
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

    @Test
    void sut_correctly_prints_one_guess_if_single_player_game_finished() {
        final var sut = new AppModel(new PositiveIntegerGeneratorStub(50));
        sut.processInput("1");
        sut.flushOutput();
        sut.processInput("50");

        final var actual = sut.flushOutput();

        assertThat(actual).contains("1 guess.");
    }

    @Test
    void sut_prints_select_mode_message_if_single_player_game_finished() {
        final var sut = new AppModel(new PositiveIntegerGeneratorStub(50));
        sut.processInput("1");
        sut.flushOutput();
        sut.processInput("50");

        final var actual = sut.flushOutput();

        assertThat(actual).endsWith(
                "1: Single player game" + NEW_LINE +
                "2: Multiplayer game" + NEW_LINE +
                "3: Exit" + NEW_LINE +
                "Enter selection: ");
    }

    @Test
    void sut_returns_to_mode_selection_if_single_player_game_finished() {
        final var sut = new AppModel(new PositiveIntegerGeneratorStub(50));

        sut.processInput("1");
        sut.processInput("50");
        sut.processInput("3");

        final var actual = sut.isCompleted();
        assertTrue(actual);
    }

    @ParameterizedTest
    @ValueSource(strings = "100, 10, 1")
    void sut_generates_answer_for_each_game(String source) {
        final var answers = Stream.of(source.split(","))
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .toArray();

        final var sut = new AppModel(new PositiveIntegerGeneratorStub(answers));
        for (int answer : answers) {
            sut.processInput("1");
            sut.flushOutput();
            sut.processInput(Integer.toString(answer));
        }

        String actual = sut.flushOutput();

        assertThat(actual).startsWith("Correct! ");
    }

    @Test
    void sut_correctly_prints_multiplayer_game_setup_message() {
        var sut = new AppModel(new PositiveIntegerGeneratorStub(50));
        sut.flushOutput();
        sut.processInput("2");

        String actual = sut.flushOutput();

        assertThat(actual).isEqualTo("Multiplayer game" + NEW_LINE + "Enter player names separated with commas: ");
    }

    @Test
    void sut_correctly_prints_multiplayer_game_start_message() {
        var sut = new AppModel(new PositiveIntegerGeneratorStub(50));
        sut.processInput("2");
        sut.flushOutput();
        sut.processInput("Foo, Bar");

        String actual = sut.flushOutput();

        assertThat(actual).startsWith("I'm thinking of a number between 1 and 100." + NEW_LINE);
    }

    @ParameterizedTest
    @CsvSource({ "Foo, Bar, Baz", "Bar, Baz, Foo", "Baz, Foo, Bar" })
    void sut_correctly_prompts_first_player_name(String player1, String player2, String player3) {
        var sut = new AppModel(new PositiveIntegerGeneratorStub(50));
        sut.processInput("2");
        sut.flushOutput();
        sut.processInput(String.join(", ", player1, player2, player3));

        String actual = sut.flushOutput();

        assertThat(actual).endsWith("Enter " + player1 + "'s guess: ");
    }

    @ParameterizedTest
    @CsvSource({ "Foo, Bar, Baz", "Bar, Baz, Foo", "Baz, Foo, Bar" })
    void sut_correctly_prompts_second_player_name(String player1, String player2, String player3) {
        var sut = new AppModel(new PositiveIntegerGeneratorStub(50));
        sut.processInput("2");
        sut.processInput(String.join(", ", player1, player2, player3));
        sut.flushOutput();
        sut.processInput("10");

        final var actual = sut.flushOutput();

        assertThat(actual).endsWith("Enter " + player2 + "'s guess: ");
    }

    @ParameterizedTest
    @CsvSource({ "Foo, Bar, Baz", "Bar, Baz, Foo", "Baz, Foo, Bar" })
    void sut_correctly_prompts_third_player_name(String player1, String player2, String player3) {
        var sut = new AppModel(new PositiveIntegerGeneratorStub(50));
        sut.processInput("2");
        sut.processInput(String.join(", ", player1, player2, player3));
        sut.processInput("90");
        sut.flushOutput();
        sut.processInput("90");

        final var actual = sut.flushOutput();

        assertThat(actual).endsWith("Enter " + player3 + "'s guess: ");
    }

    @ParameterizedTest
    @CsvSource({ "Foo, Bar, Baz", "Bar, Baz, Foo", "Baz, Foo, Bar" })
    void sut_correctly_rounds_players(String player1, String player2, String player3) {
        var sut = new AppModel(new PositiveIntegerGeneratorStub(50));
        sut.processInput("2");
        sut.processInput(String.join(", ", player1, player2, player3));
        sut.processInput("10");
        sut.processInput("10");
        sut.flushOutput();
        sut.processInput("10");

        final var actual = sut.flushOutput();

        assertThat(actual).endsWith("Enter " + player1 + "'s guess: ");
    }

    @ParameterizedTest
    @CsvSource({ "50, 40, 1, Foo", "30, 29, 2, Bar" })
    void sut_correctly_prints_too_low_message_in_multiplayer_game(int answer, int guess, int fails, String lastPlayer) {
        var sut = new AppModel(new PositiveIntegerGeneratorStub(answer));
        sut.processInput("2");
        sut.processInput("Foo, Bar, Baz");
        for (int i = 0; i < fails - 1; i++)
            sut.processInput(Integer.toString(guess));
        sut.flushOutput();
        sut.processInput(Integer.toString(guess));

        final var actual = sut.flushOutput();

        assertThat(actual).startsWith(lastPlayer + "'s guess is too low." + NEW_LINE);
    }

    @ParameterizedTest
    @CsvSource({ "50, 60, 1, Foo", "9, 81, 2, Bar" })
    void sut_correctly_prints_too_high_message_in_multiplayer_game(int answer, int guess, int fails,
                                                                   String lastPlayer) {
        var sut = new AppModel(new PositiveIntegerGeneratorStub(answer));
        sut.processInput("2");
        sut.processInput("Foo, Bar, Baz");
        for (int i = 0; i < fails - 1; i++)
            sut.processInput(Integer.toString(guess));
        sut.flushOutput();
        sut.processInput(Integer.toString(guess));

        final var actual = sut.flushOutput();

        assertThat(actual).contains(lastPlayer + "'s guess is too high." + NEW_LINE);
    }

    @ParameterizedTest
    @ValueSource(ints = { 1, 10, 100 })
    void sut_correctly_prints_correct_message_in_multiplayer_game(int answer) {
        var sut = new AppModel(new PositiveIntegerGeneratorStub(answer));
        sut.processInput("2");
        sut.processInput("Foo, Bar, Baz");
        sut.flushOutput();
        sut.processInput(Integer.toString(answer));

        final var actual = sut.flushOutput();

        assertThat(actual).startsWith("Correct! ");
    }

    @ParameterizedTest
    @CsvSource({ "0, Foo", "1, Bar", "2, Baz", "99, Foo", "100, Bar" })
    void sut_correctly_prints_winner_if_multiplayer_game_finished(int fails, String winner) {
        var sut = new AppModel(new PositiveIntegerGeneratorStub(50));
        sut.processInput("2");
        sut.processInput("Foo, Bar, Baz");
        for (int i = 0; i < fails; i++)
            sut.processInput("30");
        sut.flushOutput();
        sut.processInput("50");

        final var actual = sut.flushOutput();

        assertThat(actual).contains(winner + " wins." + NEW_LINE);
    }

    @Test
    void sut_prints_select_mode_message_if_multiplayer_game_finished() {
        var sut = new AppModel(new PositiveIntegerGeneratorStub(50));
        sut.processInput("2");
        sut.processInput("Foo, Bar, Baz");
        sut.flushOutput();
        sut.processInput("50");

        final var actual = sut.flushOutput();

        assertThat(actual).endsWith("1: Single player game" + NEW_LINE + "2: Multiplayer game" + NEW_LINE + "3: Exit"
                + NEW_LINE + "Enter selection: ");
    }

    @Test
    void sut_returns_to_mode_selection_if_multiplayer_game_finished() {
        var sut = new AppModel(new PositiveIntegerGeneratorStub(50));

        sut.processInput("2");
        sut.processInput("Foo, Bar, Baz");
        sut.processInput("20");
        sut.processInput("50");
        sut.processInput("3");

        final var actual = sut.isCompleted();
        assertTrue(actual);
    }

    //-DargLine=--add-opens java.base/java.lang=ALL-UNNAMED --add-opens java.base/java.io=ALL-UNNAMED --add-opens java.base/java.util=ALL-UNNAMED
    @Disabled
    @Test
    void print_correctly_appends_string_to_output_buffer() throws Exception {
        var sut = new AppModel(new PositiveIntegerGeneratorStub(50));
        var outputBuffer = (StringBuffer) Whitebox.getField(AppModel.class, "outputBuffer").get(sut);

        outputBuffer.setLength(0);
        Whitebox.invokeMethod(sut, "print", "foo");

        var actual = outputBuffer.toString();
        assertThat(actual).isEqualTo("foo");
    }

    @Disabled
    @Test
    void println_correctly_appends_string_and_line_separator_to_output_buffer() throws Exception {
        var sut = new AppModel(new PositiveIntegerGeneratorStub(50));
        var outputBuffer = (StringBuffer) Whitebox.getField(AppModel.class, "outputBuffer").get(sut);

        outputBuffer.setLength(0);
        Whitebox.invokeMethod(sut, "println", "foo");

        var actual = outputBuffer.toString();
        assertThat(actual).isEqualTo("foo" + System.lineSeparator());
    }

    @Disabled
    @Test
    void printLines_correctly_appends_lines() throws Exception {
        var sut = new AppModel(new PositiveIntegerGeneratorStub(50));
        var outputBuffer = (StringBuffer) Whitebox.getField(AppModel.class, "outputBuffer").get(sut);

        outputBuffer.setLength(0);
        Whitebox.invokeMethod(sut, "printLines", "foo", "bar", "baz");

        var actual = outputBuffer.toString();

        assertThat(actual).isEqualTo("foo" + NEW_LINE + "bar" + NEW_LINE + "baz");
    }
}