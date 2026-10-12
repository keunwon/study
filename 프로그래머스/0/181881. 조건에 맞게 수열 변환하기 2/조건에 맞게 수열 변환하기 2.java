import java.util.Arrays;

class Solution {
    public int solution(int[] arr) {
        var n = arr.length;
        var arr2 = Arrays.copyOf(arr, arr.length);
        var res = 1;

        next(arr2);
        while (!Arrays.equals(arr, arr2)) {
            System.arraycopy(arr2, 0, arr, 0, n);
            next(arr2);
            ++res;
        }
        return res - 1;
    }

    private void next(int[] arr) {
        for (var i = 0; i < arr.length; i++) {
            var num = arr[i];
            if (num >= 50 && num % 2 == 0) {
                arr[i] = num / 2;
            } else if (num < 50 && num % 2 == 1) {
                arr[i] = num * 2 + 1;
            }
        }
    }
}