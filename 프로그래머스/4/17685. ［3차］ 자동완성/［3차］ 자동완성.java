class Solution {
    public int solution(String[] words) {
        var rootTrie = new Trie();
        var res = 0;

        for (var w : words) {
            rootTrie.insert(w);
        }

        for (var w : words) {
            res += rootTrie.search(w);
        }
        return res;
    }

    private static class Trie {
        private final Trie[] children = new Trie[26];
        private int refs = 0;

        public void insert(String word) {
            var cur = this;
            for (var i = 0; i < word.length(); i++) {
                var c = word.charAt(i) - 'a';
                if (cur.children[c] == null) {
                    cur.children[c] = new Trie();
                }
                cur = cur.children[c];
                ++cur.refs;
            }
        }

        public int search(String word) {
            var cur = this;
            for (var i = 0; i < word.length(); i++) {
                var c = word.charAt(i) - 'a';
                cur = cur.children[c];
                if (cur.refs == 1) return i + 1;
            }
            return word.length();
        }
    }
}