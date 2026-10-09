class Solution {
    public int solution(String before, String after) {
        var count = new int[26];

        for (var i = 0; i < before.length(); i++) {
            ++count[before.charAt(i) - 'a'];
        }

        for (var i = 0; i < after.length(); i++) {
            --count[after.charAt(i) - 'a'];
        }

        for (var i = 0; i < 26; i++) {
            if (count[i] != 0) return 0;
        }
        return 1;
    }
}