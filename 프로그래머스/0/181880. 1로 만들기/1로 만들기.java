class Solution {
    public int solution(int[] num_list) {
        var res = 0;
        
        for (var num : num_list) {
            var cur = num;
            while (cur > 1) {
                if (cur % 2 == 0) {
                    cur /= 2;
                } else {
                    --cur;
                    cur /= 2;
                }
                ++res;
            }
        }
        return res;
    }
}