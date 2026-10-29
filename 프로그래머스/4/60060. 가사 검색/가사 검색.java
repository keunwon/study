class Solution {
    public int[] solution(String[] words, String[] queries) {
        var forward = new Trie[100_001];
        var backward = new Trie[100_001];

        for (var word : words) {
            var len = word.length();
            if (forward[len] == null) {
                forward[len] = new Trie();
                backward[len] = new Trie();
            }
            forward[len].insert(word, false);
            backward[len].insert(word, true);
        }

        var res = new int[queries.length];
        for (var i = 0; i < queries.length; i++) {
            var q = queries[i];
            var len = q.length();

            if (forward[len] != null) {
                res[i] = q.charAt(0) == '?' ? backward[len].search(q, true) : forward[len].search(q, false);
            }
        }
        return res;
    }

    private static class Trie {
        Node rootNode = new Node();

        public void insert(String word, boolean isReverse) {
            var len = word.length();
            var cur = rootNode;

            ++cur.count;

            for (var i = 0; i < len; i++) {
                var wIdx = isReverse ? len - i - 1 : i;
                var idx = word.charAt(wIdx) - 'a';

                cur = cur.getOrCreate(idx);
                ++cur.count;
            }
        }

        public int search(String word, boolean isReverse) {
            var len = word.length();
            var cur = rootNode;

            for (var i = 0; i < len; i++) {
                var wIdx = isReverse ? len - i - 1 : i;
                var c = word.charAt(wIdx);

                if (c == '?') {
                    return cur.count;
                }

                var idx = c - 'a';
                if (cur == null || cur.nodes[idx] == null) {
                    return 0;
                }
                cur = cur.nodes[idx];
            }
            return cur.count;
        }
    }

    private static class Node {
        Node[] nodes;
        int count = 0;

        public Node getOrCreate(int idx) {
            if (nodes == null) {
                nodes = new Node[26];
            }
            if (nodes[idx] == null) {
                nodes[idx] = new Node();
            }
            return nodes[idx];
        }
    }
}