class Solution {
    public String solution(String[] my_strings, int[][] parts) {
        var n = my_strings.length;
        var sb = new StringBuilder();

        for (var i = 0; i < n; i++) {
            var str = my_strings[i];
            var part = parts[i];
            var s = part[0];
            var e = part[1];
            sb.append(str, s, e + 1);
        }
        return sb.toString();
    }
}