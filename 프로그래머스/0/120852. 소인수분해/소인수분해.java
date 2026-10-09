import java.util.ArrayList;

class Solution {
    public int[] solution(int n) {
        var res = new ArrayList<Integer>();
        var x = 2;

        while (n != 1) {
            if (n % x == 0) {
                res.add(x);
            }

            while (n % x == 0) {
                n /= x;
            }
            ++x;
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }
}