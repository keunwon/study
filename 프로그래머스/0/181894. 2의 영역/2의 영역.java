import java.util.Arrays;

class Solution {
    public int[] solution(int[] arr) {
        var s = -1;
        var e = -1;
        
        for (var i = 0; i < arr.length; i++) {
            if (arr[i] == 2) {
                if (s == -1) s = i;
                e = i;
            }
        }
        return (s == -1) ? new int[] {-1} : Arrays.copyOfRange(arr, s, e + 1);
    }
}