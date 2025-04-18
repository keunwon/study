package algorithm.programmers;

import java.util.regex.Pattern;

public class Lesson1835 {
    private final Pattern TEXT_PATTERN = Pattern.compile("([A-Z]+)~([A-Z]+)([=<>]+)(\\d+)");

    private final char[] member = new char[]{'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    private final boolean[] visited = new boolean[member.length];

    private int answer = 0;

    public int solution(int n, String[] data) {
        dfs(0, "", data);
        return answer;
    }

    private void dfs(int depth, String target, String[] data) {
        if (depth == member.length) {
            if (check(target, data)) ++answer;
            return;
        }

        for (int i = 0; i < member.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(depth + 1, target + member[i], data);
                visited[i] = false;
            }
        }
    }

    private boolean check(String target, String[] data) {
        for (var s : data) {
            var arr = TEXT_PATTERN.matcher(s);
            if (!arr.matches()) return false;
            var str1 = arr.group(1);
            var str2 = arr.group(2);
            var operator = arr.group(3);
            var num = Integer.parseInt(arr.group(4));

            var idx1 = target.indexOf(str1);
            var idx2 = target.indexOf(str2);
            var diff = Math.abs(idx1 - idx2) - 1;

            if (operator.equals("=") && diff != num) return false;
            else if (operator.equals(">") && diff <= num) return false;
            else if (operator.equals("<") && diff >= num) return false;
        }
        return true;
    }
}
