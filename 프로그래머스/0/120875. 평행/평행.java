class Solution {
    public int solution(int[][] dots) {
        if (getInclination(dots[0], dots[1]) == getInclination(dots[2], dots[3])) {
            return 1;
        } else if (getInclination(dots[0], dots[2]) == getInclination(dots[1], dots[3])) {
            return 1;
        } else if (getInclination(dots[0], dots[3]) == getInclination(dots[1], dots[2])) {
            return 1;
        } else {
            return 0;
        }
    }

    private double getInclination(int[] dot1, int[] dot2) {
        return (double) Math.abs(dot1[0] - dot2[0]) / Math.abs(dot1[1] - dot2[1]);
    }
}