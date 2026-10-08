class Solution {
    public String solution(String my_string) {
        var count = new int[26];
        for (var c : my_string.toLowerCase().toCharArray()) {
            ++count[c - 'a'];
        }

        var sb = new StringBuilder(my_string.length());
        for (var i = 0; i < 26; i++) {
            if (count[i] > 0) {
                sb.repeat('a' + i, count[i]);
            }
        }
        return sb.toString();
    }
}