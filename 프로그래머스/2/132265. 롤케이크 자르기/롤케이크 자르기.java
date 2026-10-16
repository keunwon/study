import java.util.HashMap;

class Solution {
    public int solution(int[] topping) {
        var countMap = new HashMap<Integer, Integer>();
        var curMap = new HashMap<Integer, Integer>();

        for (var t : topping) {
            var n = countMap.getOrDefault(t, 0);
            countMap.put(t, n + 1);
        }

        var res = 0;
        for (var t : topping) {
            var n = countMap.get(t);
            if (n == 1) countMap.remove(t);
            else countMap.put(t, n - 1);

            curMap.put(t, curMap.getOrDefault(t, 0) + 1);
            if (curMap.size() == countMap.size()) {
                ++res;
            }
        }
        return res;
    }
}