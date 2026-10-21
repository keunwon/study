import java.util.Stack;

class Solution {
    public String solution(int n, int k, String[] cmd) {
        var undo = new Stack<Integer>();
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

        var sb = new StringBuilder();
        sb.repeat("O", n + 1);
        while (!undo.isEmpty()) {
            sb.insert(undo.pop(), "X");
        }
        return sb.toString();
    }
}