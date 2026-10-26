class Solution {
    public int solution(int[] queue1, int[] queue2) {
        var len = queue1.length;
        var sum1 = 0L;
        var sum2 = 0L;

        for (var i = 0; i < len; i++) {
            sum1 += queue1[i];
            sum2 += queue2[i];
        }

        var total = sum1 + sum2;
        if ((total & 1) == 1) {
            return -1;
        }

        var target = total / 2;
        var curSum = sum1;
        var p1 = 0;
        var p2 = len;
        var limit = len * 3;

        for (var i = 0; i < limit; i++) {
            if (curSum == target) {
                return i;
            }

            if (curSum > target) {
                var idx = p1 % (len * 2);
                curSum -= idx < len ? queue1[idx] : queue2[idx - len];
                ++p1;
            } else {
                var idx = p2 % (len * 2);
                curSum += idx < len ? queue1[idx] : queue2[idx - len];
                ++p2;
            }
        }
        return -1;
    }
}