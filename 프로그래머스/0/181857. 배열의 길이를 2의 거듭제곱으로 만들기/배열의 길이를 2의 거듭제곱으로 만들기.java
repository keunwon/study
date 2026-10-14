import java.util.Arrays;

class Solution {
    public int[] solution(int[] arr) {
        int targetLength = 1 << (32 - Integer.numberOfLeadingZeros(arr.length - 1));
        return Arrays.copyOf(arr, targetLength);
    }
}