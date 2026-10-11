import java.util.HashMap;

class Solution {
    public int[] solution(String[] gems) {
        var n = gems.length;
        var types = 0;
        var gemMap = new HashMap<String, Integer>();
        var gemIds = new int[n];

        for (var i = 0; i < gems.length; i++) {
            var gem = gems[i];
            var id = gemMap.get(gem);

            if (id == null) {
                id = types++;
                gemMap.put(gem, id);
            }
            gemIds[i] = id;
        }

        var count = new int[types];
        var distinctCount = 0;

        var minLen = Integer.MAX_VALUE;
        var bestLeft = 0;
        var bestRight = 0;
        var left = 0;

        for (var right = 0; right < n; right++) {
            var gemId = gemIds[right];

            if (count[gemId] == 0) {
                ++distinctCount;
            }
            ++count[gemId];

            if (distinctCount == types) {
                while (count[gemIds[left]] > 1) {
                    --count[gemIds[left]];
                    ++left;
                }

                var len = right - left;
                if (minLen > len) {
                    minLen = len;
                    bestLeft = left;
                    bestRight = right;
                }
            }
        }
        return new int[] {bestLeft + 1, bestRight + 1};
    }
}