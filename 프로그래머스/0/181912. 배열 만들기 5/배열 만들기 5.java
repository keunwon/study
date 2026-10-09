import java.util.ArrayList;

class Solution {
    public int[] solution(String[] intStrs, int k, int s, int l) {
        var res = new ArrayList<Integer>();
        for (var intStr : intStrs) {
            var num = 0;
            for (var i = s; i < s + l; i++) {
                num = num * 10 + (intStr.charAt(i) - '0');
            }
            if (num > k) res.add(num);
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }
}