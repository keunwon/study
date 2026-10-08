import java.util.ArrayList;

class Solution {
    public int[] solution(int n, int[] numlist) {
        var res = new ArrayList<Integer>();
        for (var num : numlist) {
            if (num % n == 0) res.add(num);
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }
}