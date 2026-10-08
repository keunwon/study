class Solution {
    public String solution(String my_string) {
        var n = my_string.length();
        var sb = new StringBuilder(n);
        
        for (var i = 0; i < n; i++) {
            var c = my_string.charAt(i);
            if (c != 'a' && c != 'e' && c != 'i' && c != 'o' && c != 'u') {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}