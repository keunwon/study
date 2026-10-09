import java.util.ArrayList;

class Solution {
    public int[] solution(String[] intStrs, int k, int s, int l) {
        var res = new ArrayList<Integer>();
        for (var intStr : intStrs) {
            var num = Integer.parseInt(intStr.substring(s, s + l));
            if (num > k) res.add(num);
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }
}