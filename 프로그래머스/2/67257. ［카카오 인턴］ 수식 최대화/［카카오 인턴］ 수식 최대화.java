import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    private List<Long> numbers;
    private List<Character> operators;

    private long maxRes = 0L;

    public long solution(String expression) {
        this.numbers =
                Arrays.stream(expression.split("[-+*]")).map(Long::parseLong).toList();
        this.operators =
                expression.replaceAll("\\d", "").chars().mapToObj(c -> (char) c).toList();

        dfs(new char[3], new boolean[3], 0);
        return maxRes;
    }

    private void dfs(char[] pick, boolean[] visited, int depth) {
        if (depth == 3) {
            var tmpNumbers = new ArrayList<>(numbers);
            var tmpOperators = new ArrayList<>(operators);

            for (var p : pick) {
                var oIdx = 0;
                while (oIdx < tmpOperators.size()) {
                    var op = tmpOperators.get(oIdx);
                    if (op != p) {
                        ++oIdx;
                        continue;
                    }

                    var a = tmpNumbers.remove(oIdx);
                    var b = tmpNumbers.remove(oIdx);
                    var calcNum = switch (op) {
                        case '-' -> a - b;
                        case '+' -> a + b;
                        case '*' -> a * b;
                        default -> -1;
                    };
                    tmpNumbers.add(oIdx, calcNum);
                    tmpOperators.remove(oIdx);
                }
            }

            maxRes = Math.max(maxRes, Math.abs(tmpNumbers.getFirst()));
            return;
        }

        var op = "-+*";
        for (var i = 0; i < 3; i++) {
            if (!visited[i]) {
                visited[i] = true;
                pick[depth] = op.charAt(i);
                dfs(pick, visited, depth + 1);
                visited[i] = false;
            }
        }
    }
}