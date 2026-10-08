class Solution {
    public int solution(int[] num_list) {
        if (num_list.length >= 11) {
            var res = 0;
            for (var num : num_list) res += num;
            return res;
        }
        
        var res = 1;
        for (var num : num_list) res *= num;
        return res;
    }
}