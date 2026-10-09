class Solution {
    public String[] solution(String my_str, int n) {
        var m = (my_str.length() + n - 1) / n;
        var res = new String[m];

        for (var i = 0; i < m; i++) {
            var s = i * n;
            var e = Math.min(s + n, my_str.length());
            res[i] = my_str.substring(s, e);
        }
        return res;
    }
}