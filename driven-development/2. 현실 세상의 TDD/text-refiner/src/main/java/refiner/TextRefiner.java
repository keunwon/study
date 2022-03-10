package refiner;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class TextRefiner {

    public static String refinerText(String s) {
        return refinerText(s, Map.of());
    }

    public static String refinerText(String source, Map<String, Object> options) {
        return Stream.of(source)
                .map(TextRefiner::normalizeWhiteSpace)
                .map(TextRefiner::compactWhiteSpace)
                .map(x -> maskBannedWords(x, options))
                .findAny().get();
    }

    private static String maskBannedWords(String source, Map<String, Object> options) {
        if (options.containsKey("bannedWords")) {
            final var bannedWords = options.get("bannedWords");

            if (bannedWords instanceof List<?> list) {
                return list.stream()
                        .map(word -> (String) word)
                        .reduce(source, TextRefiner::maskBannedWord);
            }
        }
        return source;
    }

    private static String maskBannedWord(String source, String bannedWord) {
        final var mask = "*".repeat(bannedWord.length());
        return source.replaceAll(bannedWord, mask);
    }

    private static String normalizeWhiteSpace(String source) {
        return source.replaceAll("\t", " ");
    }

    private static String compactWhiteSpace(String source) {
        return !source.contains("  ")
                ? source
                : compactWhiteSpace(source.replaceAll(" {2}", " "));
    }
}
