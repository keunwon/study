import java.util.ArrayList;

class Solution {
    public int[] solution(int n) {
        var res = new ArrayList<Integer>();
        for (var i = 1; i <= n; i += 2) {
            res.add(i);
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }
}