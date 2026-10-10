import java.util.HashMap;

class Solution {
    public int solution(int k, int[] tangerine) {
        var count = new HashMap<Integer, Integer>(tangerine.length);
        for (var t : tangerine) {
            count.put(t, count.getOrDefault(t, 0) + 1);
        }

        var list = count.values().stream().sorted().toList();
        var res = 0;

        for (var i = list.size() - 1; i >= 0; i--) {
            ++res;
            k -= list.get(i);
            if (k <= 0) break;
        }
        return res;
    }
}