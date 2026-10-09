class Solution {
    public int solution(int[] date1, int[] date2) {
        for (var i = 0; i < 3; i++) {
            var d1 = date1[i];
            var d2 = date2[i];
            
            if (d1 < d2) return 1;
            else if (d1 > d2) return 0;
        }
        return 0;
    }
}