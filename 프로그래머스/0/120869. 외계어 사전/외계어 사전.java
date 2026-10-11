import java.util.Arrays;

class Solution {
    public int solution(String[] spell, String[] dic) {
        StringBuilder sb = new StringBuilder();
        for (var s : spell) {
            sb.append(s);
        }

        var targetHash = generateHash(sb.toString());
        for (var d : dic) {
            var dicHash = generateHash(d);
            if (targetHash == dicHash) return 1;
        }
        return 2;
    }

    private int generateHash(String str) {
        var alphabet = new boolean[26];
        for (var i = 0; i < str.length(); i++) {
            var c = str.charAt(i) - 'a';
            alphabet[c] = true;
        }
        return Arrays.hashCode(alphabet);
    }
}