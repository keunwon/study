import java.util.Arrays;

class Solution {
    private String[] words;
    private boolean[] visited;
    private String target;

    private int res = -1;

    public int solution(String begin, String target, String[] words) {
        this.words = words;
        this.visited = new boolean[words.length];
        this.target = target;

        Arrays.sort(words);

        dfs(begin, 0);
        return res == -1 ? 0 : res;
    }

    private void dfs(String cur, int depth) {
        if (res > -1) return;

        if (cur.equals(target)) {
            res = depth;
            return;
        }

        for (var i = 0; i < words.length; i++) {
            if (visited[i]) {
                continue;
            }

            var word = words[i];
            var diffCount = 0;

            for (var j = 0; j < word.length(); j++) {
                if (cur.charAt(j) != word.charAt(j)) {
                    ++diffCount;
                }
            }

            if (diffCount == 1) {
                visited[i] = true;
                dfs(word, depth + 1);
                visited[i] = false;
            }
        }
    }
}