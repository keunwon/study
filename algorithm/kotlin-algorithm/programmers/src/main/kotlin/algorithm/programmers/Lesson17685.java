package algorithm.programmers;

import java.util.Arrays;

public class Lesson17685 {
    public int solution(String[] words) {
        var trie = new Trie();
        for (var word : words) {
            trie.insert(word);
        }
        return Arrays.stream(words).mapToInt(trie::find).sum();
    }

    private static class Trie {
        private final Trie[] childs = new Trie[26];
        private int count = 0;

        public void insert(String word) {
            var node = this;

            for (int i = 0; i < word.length(); i++) {
                var c = word.charAt(i) - 'a';

                if (node.childs[c] == null) {
                    node.childs[c] = new Trie();
                }
                node = node.childs[c];
                ++node.count;
            }
        }

        public int find(String word) {
            var node = this;

            for (int i = 0; i < word.length(); i++) {
                var tmpIndex = word.charAt(i) - 'a';
                node = node.childs[tmpIndex];

                if (node.count == 1) return i + 1;
            }
            return word.length();
        }
    }
}
