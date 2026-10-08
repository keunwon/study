class Solution {
    public int solution(int[] numbers, int n) {
        var cur = 0;
        
        for (var num: numbers) {
            cur += num;
            if (cur > n) break;
        }
        return cur;
    }
}