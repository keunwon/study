import java.util.ArrayList;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        var n = progresses.length;
        var days = new int[n];

        for (var i = 0; i < n; i++) {
            days[i] = (100 - progresses[i] + speeds[i] - 1) / speeds[i];
        }

        var count = 1;
        var prev = days[0];
        var res = new ArrayList<Integer>();

        for (var i = 1; i < n; i++) {
            if (prev >= days[i]) {
                ++count;
            } else {
                res.add(count);
                count = 1;
                prev = days[i];
            }
        }
        res.add(count);
        
        return res.stream().mapToInt(Integer::intValue).toArray();
    }
}