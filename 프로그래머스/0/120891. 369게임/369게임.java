class Solution {
    public int solution(int order) {
        var count = 0;
        while (order > 0) {
            var pop = order % 10;
            if (pop == 3 || pop == 6 || pop == 9) {
                ++count;
            }
            order /= 10;
        }
        return count;
    }
}