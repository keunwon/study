import java.util.Arrays;

class Solution {
    public int[] solution(int[] arr) {
        var top = -1;
        for (var i = 0; i < arr.length; i++) {
            var num = arr[i];
            if (top > -1 && num == arr[top]) {
                --top;
            } else {
                arr[++top] = num;
            }
        }
        return top == -1 ? new int[] {-1} : Arrays.copyOf(arr, top + 1);
    }
}