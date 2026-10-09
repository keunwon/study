import java.util.ArrayList;

class Solution {
    public int[] solution(int[] arr, boolean[] flag) {
        var n = arr.length;
        var res = new ArrayList<Integer>();

        for (var i = 0; i < n; i++) {
            var num = arr[i];
            if (flag[i]) {
                for (var j = 0; j < num * 2; j++) {
                    res.add(num);
                }
            } else {
                for (var j = 0; j < num; j++) {
                    res.removeLast();
                }
            }
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }
}