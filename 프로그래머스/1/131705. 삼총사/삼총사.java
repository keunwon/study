import java.util.Arrays;

class Solution {
    public int solution(int[] number) {
        var n = number.length;
        var res = 0;

        for (var i = 0; i < n; i++) {
            for (var j = i + 1; j < n; j++) {
                for (var k = j + 1; k < n; k++) {
                    var sum = number[i] + number[j] + number[k];
                    if (sum == 0) ++res;
                }
            }
        }
        return res;
    }
}