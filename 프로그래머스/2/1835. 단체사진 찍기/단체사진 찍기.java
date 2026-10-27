class Solution {
    private final char[] letters = new char[] {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};

    private String[] data;
    private int res = 0;

    public int solution(int n, String[] data) {
        this.data = data;
        dfs(new int[26], new boolean[8], 0);
        return res;
    }

    private void dfs(int[] alphabet, boolean[] visited, int depth) {
        if (depth == 8) {
            var valid = true;

            for (var str : data) {
                var c1 = str.charAt(0);
                var c2 = str.charAt(2);
                var op = str.charAt(3);
                var x = str.charAt(4) - '0';

                var idx1 = alphabet[c1 - 'A'];
                var idx2 = alphabet[c2 - 'A'];
                var d = Math.abs(idx1 - idx2) - 1;

                switch (op) {
                    case '=' -> valid = x == d;
                    case '>' -> valid = d > x;
                    case '<' -> valid = d < x;
                }

                if (!valid) break;
            }

            if (valid) ++res;
            return;
        }

        for (var i = 0; i < letters.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                alphabet[letters[i] - 'A'] = depth;
                dfs(alphabet, visited, depth + 1);
                visited[i] = false;
            }
        }
    }
}