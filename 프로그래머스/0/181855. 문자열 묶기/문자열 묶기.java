class Solution {
    public int solution(String[] strArr) {
        var count = new int[31];
        var max = 0;
        
        for (var str : strArr) {
            var len = str.length();
            ++count[len];
            max = Math.max(max, count[len]);
        }
        return max;
    }
}