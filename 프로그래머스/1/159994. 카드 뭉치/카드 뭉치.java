class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        var idx1 = 0;
        var idx2 = 0;
        var gIdx = 0;

        while (gIdx < goal.length) {
            if (idx1 < cards1.length && cards1[idx1].equals(goal[gIdx])) {
                ++gIdx;
                ++idx1;
            } else if (idx2 < cards2.length && cards2[idx2].equals(goal[gIdx])) {
                ++gIdx;
                ++idx2;
            } else {
                return "No";
            }
        }
        return "Yes";
    }
}