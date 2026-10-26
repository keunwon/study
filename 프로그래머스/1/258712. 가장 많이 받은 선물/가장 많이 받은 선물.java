import java.util.HashMap;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        var n = friends.length;
        var idxMap = new HashMap<String, Integer>(n);
        var point = new int[n];
        var history = new int[n][n];

        for (var i = 0; i < n; i++) {
            var f = friends[i];
            idxMap.put(f, i);
        }

        for (var gift : gifts) {
            var tokens = gift.split(" ");
            var idx1 = idxMap.get(tokens[0]);
            var idx2 = idxMap.get(tokens[1]);

            ++point[idx1];
            --point[idx2];
            ++history[idx1][idx2];
        }

        var maxValue = 0;
        for (var i = 0; i < n; i++) {
            var value = 0;
            for (var j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }

                var h1 = history[i][j];
                var h2 = history[j][i];
                if (h1 > h2 || (h1 == h2 && point[i] > point[j])) {
                    ++value;
                }
            }
            maxValue = Math.max(maxValue, value);
        }
        return maxValue;
    }
}