class Solution {
    public int solution(int n, int[] stations, int w) {
        var pos = 0;
        var res = 0;
        var sIdx = 0;

        while (pos < n) {
            if (sIdx < stations.length && pos >= stations[sIdx] - w - 1) {
                pos = Math.max(pos, stations[sIdx] + w);
                ++sIdx;
            } else {
                pos = pos + (w * 2) + 1;
                ++res;
            }
        }
        return res;
    }
}