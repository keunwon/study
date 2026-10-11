class Solution {
    public int solution(int[][] lines) {
        var count = new int[201];

        for (var line : lines) {
            ++count[line[0] + 100];
            --count[line[1] + 100];
        }

        for (var i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        var res = 0;
        for (int num : count) {
            if (num > 1) ++res;
        }
        return res;
    }
}