class Solution {
    public String solution(String s) {
        var count = new int[26];
        for (var i = 0; i < s.length(); i++) {
            ++count[s.charAt(i) - 'a'];
        }

        var sb = new StringBuilder(26);
        for (var i = 0; i < 26; i++) {
            if (count[i] == 1) {
                sb.append((char) ('a' + i));
            }
        }
        return sb.toString();
    }
}