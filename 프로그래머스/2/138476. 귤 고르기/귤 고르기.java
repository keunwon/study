import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

class Solution {
    public int solution(int k, int[] tangerine) {
        var count = new HashMap<Integer, Integer>(tangerine.length * 2);
        for (var t : tangerine) {
            count.put(t, count.getOrDefault(t, 0) + 1);
        }

        var list = new ArrayList<>(count.values());
        list.sort(Comparator.reverseOrder());

        for (var i = 0; i < list.size(); i++) {
            k -= list.get(i);
            if (k <= 0) return i + 1;
        }
        return list.size();
    }
}