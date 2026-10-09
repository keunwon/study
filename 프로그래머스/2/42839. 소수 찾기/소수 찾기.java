import java.util.HashSet;
import java.util.Set;

class Solution {
    private Set<Integer> set;

    public int solution(String numbers) {
        this.set = new HashSet<>();
        dfs(numbers, new boolean[numbers.length()], 0);
        return set.size();
    }

    private void dfs(String numbers, boolean[] visited, int cur) {
        var isPrime = true;
        for (var i = 2; i <= Math.sqrt(cur); i++) {
            if (cur % i == 0) {
                isPrime = false;
                break;
            }
        }
        if (cur >= 2 && isPrime) set.add(cur);

        for (var i = 0; i < numbers.length(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                var next = cur * 10 + (numbers.charAt(i) - '0');
                dfs(numbers, visited, next);
                visited[i] = false;
            }
        }
    }
}