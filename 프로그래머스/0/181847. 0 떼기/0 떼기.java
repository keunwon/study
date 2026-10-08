class Solution {
    public String solution(String n_str) {
        var n = n_str.length();
        for (var i = 0; i < n; i++) {
            if (n_str.charAt(i) != '0') {
                return n_str.substring(i, n);
            }
        }
        return n_str;
    }
}