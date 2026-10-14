import java.util.ArrayList;

class Solution {
    public int[] solution(int l, int r) {
        var res = new ArrayList<Integer>();

        for (var i = l; i <= r; i++) {
            var cur = i;
            var valid = true;

            while (cur > 0) {
                var pop = cur % 10;
                if (pop != 0 && pop != 5) {
                    valid = false;
                    break;
                }
                cur /= 10;
            }
            if (valid) res.add(i);
        }

        return res.isEmpty()
                ? new int[] {-1}
                : res.stream().mapToInt(Integer::intValue).toArray();
    }
}