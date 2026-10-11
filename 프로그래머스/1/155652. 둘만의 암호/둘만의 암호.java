class Solution {
    public String solution(String s, String skip, int index) {
        var skipArr = new boolean[26];
        for (var i = 0; i < skip.length(); i++) {
            var c = skip.charAt(i);
            skipArr[c - 'a'] = true;
        }

        var letters = s.toCharArray();
        for (var i = 0; i < s.length(); i++) {
            var c = s.charAt(i);
            var cnt = 0;

            while (cnt < index) {
                ++c;
                if (c > 'z') c = 'a';
                if (!skipArr[c - 'a']) ++cnt;
            }
            letters[i] = c;
        }
        return new String(letters);
    }
}