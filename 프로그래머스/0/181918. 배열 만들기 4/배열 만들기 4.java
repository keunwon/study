import java.util.Arrays;

class Solution {
    public int[] solution(int[] arr) {
        var top = -1;
        var i = 0;

        while (i < arr.length) {
            if (top == -1) {
                arr[++top] = arr[i++];
            } else if (arr[top] < arr[i]) {
                arr[++top] = arr[i++];
            } else {
                --top;
            }
        }
        return Arrays.copyOf(arr, top + 1);
    }
}