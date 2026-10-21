import java.util.ArrayDeque;

class Solution {
    public String solution(int n, int k, String[] cmd) {
        var undo = new ArrayDeque<Integer>(n);
        --n;

        for (var str : cmd) {
            var tokens = str.split(" ");

            switch (tokens[0]) {
                case "U" -> {
                    var x = Integer.parseInt(tokens[1]);
                    k = Math.max(k - x, 0);
                }

                case "D" -> {
                    var x = Integer.parseInt(tokens[1]);
                    k = Math.min(n, k + x);
                }

                case "C" -> {
                    undo.push(k);
                    --n;
                    if (n < k) --k;
                }

                case "Z" -> {
                    ++n;
                    var pos = undo.pop();
                    if (pos <= k) ++k;
                }
            }
        }

        var sb = new StringBuilder(n + 1 + undo.size());
        sb.repeat("O", n + 1);
        while (!undo.isEmpty()) {
            sb.insert(undo.pop(), "X");
        }
        return sb.toString();
    }
}