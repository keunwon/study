import java.util.Arrays;

class Solution {
    public int solution(int[] citations) {
        Arrays.sort(citations);
        
        var n = citations.length;
        for (var i = 0; i < citations.length; i++) {
            var hIdx = n - i;
            if (citations[i] >= hIdx) return hIdx;
        }
        return 0;
    }
}