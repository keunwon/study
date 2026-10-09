import java.util.ArrayList;

class Solution {
    public int[] solution(int[] arr, int[][] intervals) {
        var res = new ArrayList<Integer>();
        for (var interval : intervals) {
            for (var i = interval[0]; i <= interval[1]; i++) {
                res.add(arr[i]);
            }
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }
}