import java.util.ArrayList;

class Solution {
    public int[] solution(int[] arr) {
        var res = new ArrayList<Integer>();
        for (var num : arr) {
            for (var i = 0; i < num; i++) {
                res.add(num);
            }
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }
}