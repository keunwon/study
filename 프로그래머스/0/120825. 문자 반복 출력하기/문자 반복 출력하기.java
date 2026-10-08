class Solution {
    public String solution(String my_string, int n) {
        var sb = new StringBuilder(my_string.length() * n);
        for (var i = 0; i < my_string.length(); i++) {
            var c = my_string.charAt(i);
            sb.repeat(c, n);
        }
        return sb.toString();
    }
}