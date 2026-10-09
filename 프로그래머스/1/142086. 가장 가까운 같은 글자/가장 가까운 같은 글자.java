import java.util.Arrays;

class Solution {
    public int[] solution(String s) {
        var latestUsed = new int[26];
        var res = new int[s.length()];

        Arrays.fill(latestUsed, -1);

        for (var i = 0; i < s.length(); i++) {
            var c = s.charAt(i) - 'a';
            res[i] = latestUsed[c] == -1 ? -1 : i - latestUsed[c];
            latestUsed[c] = i;
        }
        return res;
    }
}