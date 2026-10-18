class Solution {
    public int solution(int[] sides) {
        var a = Math.min(sides[0], sides[1]);
        var b = Math.max(sides[0], sides[1]);
        return (b - (b - a)) + (a + b - b - 1);
    }
}