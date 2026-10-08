class Solution {
    public int solution(String num_str) {
        var res = 0;
        for (var i = 0; i < num_str.length(); i++) {
            res += num_str.charAt(i) - '0';
        }
        return res;
    }
}