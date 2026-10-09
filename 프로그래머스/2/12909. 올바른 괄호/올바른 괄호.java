class Solution {
    boolean solution(String s) {
        var left = 0;
        for (var i = 0;i < s.length(); i++) {
            var c = s.charAt(i);
            if (c == '(') {
                ++left;
            } else {
                if (left == 0) return false;
                --left;
            }
        }
        return left == 0;
    }
}