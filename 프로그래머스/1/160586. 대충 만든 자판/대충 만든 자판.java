import java.util.Arrays;

class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        var alphabet = new int[26];
        Arrays.fill(alphabet, Integer.MAX_VALUE);

        for (var key : keymap) {
            for (var i = 0; i < key.length(); i++) {
                var c = key.charAt(i) - 'A';
                alphabet[c] = Math.min(alphabet[c], i + 1);
            }
        }

        var res = new int[targets.length];
        for (var i = 0; i < targets.length; i++) {
            var target = targets[i];
            var total = 0;
            
            for (var j = 0; j < target.length(); j++) {
                var c = target.charAt(j) - 'A';
                if (alphabet[c] == Integer.MAX_VALUE) {
                    total = -1;
                    break;
                } else {
                    total += alphabet[c];
                }
            }
            res[i] = total;
        }
        return res;
    }
}