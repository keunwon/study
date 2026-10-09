import java.util.Arrays;

class Solution {
    public int solution(int[][] sizes) {
        var n1 = Integer.MIN_VALUE;
        var n2 = Integer.MIN_VALUE;
        
        for (var size : sizes) {
            Arrays.sort(size);
            n1 = Math.max(n1, size[0]);
            n2 = Math.max(n2, size[1]);
        }
        return n1 * n2;
    }
}