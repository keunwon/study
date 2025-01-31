package refiner;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TextRefinerTest {

    @DisplayName("공백이 포함된 단어 치환")
    @ParameterizedTest
    @CsvSource(textBlock = """
            hello  world,       hello world
            hello   world,      hello world
            hello    world,     hello world
            hello     world,    hello world
            hello      world,   hello world
            hello       world,  hello world
    """)
    void case01(String source, String expect) {
        final var actual = TextRefiner.refinerText(source);

        assertEquals(expect, actual);
    }

    @DisplayName("Tab + 공백이 포함된 단어 치환")
    @ParameterizedTest
    @CsvSource({
            "hello\t world,  hello world",
            "hello \tworld, hello world",
    })
    void that_contains_tab_character(String source, String expect) {
        final var actual = TextRefiner.refinerText(source);

        assertEquals(expect, actual);
     }

     @DisplayName("공백이 포함된 단어 치환 및 마스킹 처리")
     @ParameterizedTest
     @MethodSource("ofMasking")
    void case3(String source, List<String> bannedWords, String expected) {
        final var options = Map.of("bannedWords", (Object) bannedWords);
        final var actual = TextRefiner.refinerText(source, options);

        assertEquals(expected, actual);
    }

    @DisplayName("공백이 포함된 '랜덤' 단어 치환 및 마스킹 처리")
    @ParameterizedTest
    @MethodSource("randomMaskingWord")
    void case4(String source, List<String> bannedWords, String expected) {
        final var options = Map.of("bannedWords", (Object) bannedWords);
        final var actual = TextRefiner.refinerText(source, options);

        assertEquals(expected, actual);
    }

    @DisplayName("단어 앞에 공백이 포함")
    @ParameterizedTest
    @CsvSource(value = {"hello world, hello world"}, ignoreLeadingAndTrailingWhitespace = false)
    void case05(String expected, String source) {
        final var actual = TextRefiner.refinerText(source);

        assertEquals(expected, actual);
    }

    private static Stream<Arguments> ofMasking() {
        return Stream.of(
                Arguments.of("hello mockist", List.of("mockist", "purist"), "hello *******"),
                Arguments.of("hello purist", List.of("mockist", "purist"), "hello ******")
        );
    }

    private static Stream<Arguments> randomMaskingWord() {
        final var bannedWord = new Faker().lorem().word();
        final var source = "hello %s".formatted(bannedWord);
        final var expected = "hello %s".formatted("*".repeat(bannedWord.length()));

        return Stream.of(
                Arguments.of(source, List.of(bannedWord), expected)
        );
    }
}
