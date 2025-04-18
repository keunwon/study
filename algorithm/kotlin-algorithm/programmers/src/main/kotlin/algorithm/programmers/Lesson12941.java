package algorithm.programmers;

import java.util.Arrays;

public class Lesson12941 {
    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);

        var sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += (A[i] * B[B.length - 1 - i]);
        }
        return sum;
    }
}
