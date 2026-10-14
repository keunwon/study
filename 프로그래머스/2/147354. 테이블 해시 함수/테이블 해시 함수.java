import java.util.Arrays;

import static java.util.Comparator.comparingInt;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        Arrays.sort(data, comparingInt((int[] o) -> o[col - 1]).thenComparingInt(o -> -o[0]));

        var res = 0;
        for (var i = row_begin - 1; i < row_end; i++) {
            var total = 0;
            for (var d : data[i]) {
                total += (d % (i + 1));
            }
            res = res ^ total;
        }
        return res;
    }
}