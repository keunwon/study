class Solution {
    public int[] solution(int[] sequence, int k) {
        var res = new int[] {0, sequence.length - 1};
        var l = 0;
        var curSum = 0;

        for (var r = 0; r < sequence.length; r++) {
            curSum += sequence[r];

            while (curSum > k) {
                curSum -= sequence[l++];
            }

            if (curSum == k && res[1] - res[0] > r - l) {
                res[0] = l;
                res[1] = r;
            }
        }
        return res;
    }
}