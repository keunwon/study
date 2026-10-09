class Solution {
    public int solution(int[] array) {
        var count = 0;
        
        for (var num : array) {
            var cur = num;
            while (cur > 0) {
                if (cur % 10 == 7) {
                    ++count;
                }
                cur /= 10;
            }
        }
        return count;
    }
}