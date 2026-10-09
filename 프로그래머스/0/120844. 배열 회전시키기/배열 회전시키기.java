class Solution {
    public int[] solution(int[] numbers, String direction) {
        var n = numbers.length;
        var res = new int[n];
        var sIdx = direction.equals("left") ? 1 : n - 1;
        
        for (var i = 0; i < n; i++) {
            res[i] = numbers[(i + sIdx) % n];
        }
        return res;
    }
}