import java.util.HashSet;

class Solution {
    public int[] solution(int n, String[] words) {
        var res = new int[2];
        var prevChar = words[0].charAt(0);
        var set = new HashSet<String>(words.length);

        for (var i = 0; i < words.length; i++) {
            var word = words[i];
            if (word.charAt(0) == prevChar && !set.contains(word)) {
                prevChar = word.charAt(word.length() - 1);
                set.add(word);
            } else {
                res[0] = i % n + 1;
                res[1] = i / n + 1;
                break;
            }
        }
        return res;
    }
}