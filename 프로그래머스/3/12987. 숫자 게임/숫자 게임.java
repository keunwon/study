import java.util.Arrays;

class Solution {
    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);

        var bIdx = 0;
        var res = 0;

        for (int num : A) {
            while (bIdx < B.length && B[bIdx] <= num) {
                ++bIdx;
            }

            if (bIdx == B.length) break;

            ++res;
            ++bIdx;
        }
        return res;
    }
}