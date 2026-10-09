import java.util.Arrays;

class Solution {
    public int solution(int []A, int []B) {
        Arrays.sort(A);
        Arrays.sort(B);
        
        var n = A.length;
        var res = 0;
        
        for (var i = 0; i < n; i++) {
            res += A[i] * B[n - i - 1];
        }
        return res;
    }
}