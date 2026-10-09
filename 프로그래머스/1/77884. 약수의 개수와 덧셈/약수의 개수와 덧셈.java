class Solution {
    public int solution(int left, int right) {
        var count = new int[right + 1];
        for (var i = 1; i <= right; i++) {
            for (var j = i; j <= right; j += i) {
                ++count[j];
            }
        }
        
        var res = 0;
        for (var i = left; i <= right; i++) {
            if (count[i] % 2 == 0) {
                res += i;
            } else {
                res -= i;
            }
        }
        return res;
    }
}