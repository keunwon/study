import java.util.ArrayList;

class Solution {
    public int[] solution(int n) {
        var res = new ArrayList<Integer>();
        res.add(n);

        while (n != 1) {
            if (n % 2 == 0) {
                n /= 2;
            } else {
                n = 3 * n + 1;
            }
            res.add(n);
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }
}