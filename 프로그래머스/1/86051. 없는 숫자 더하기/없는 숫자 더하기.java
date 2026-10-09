class Solution {
    public int solution(int[] numbers) {
        var res = 45;
        for (var num : numbers) {
            res -= num;
        }
        return res;
    }
}