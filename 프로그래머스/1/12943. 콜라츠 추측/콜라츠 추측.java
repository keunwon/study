class Solution {
    public int solution(int num) {
        var res = 0;
        var cur = (long) num;

        while (cur != 1L) {
            if (cur % 2 == 0L) {
                cur /= 2;
            } else {
                cur = cur * 3 + 1;
            }
            
            if (++res > 500) return -1;
        }
        return res;
    }
}