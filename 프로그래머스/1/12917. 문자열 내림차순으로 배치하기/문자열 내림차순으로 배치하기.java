import java.util.Arrays;

class Solution {
    public String solution(String s) {
        var arr = s.toCharArray();
        Arrays.sort(arr);

        var sb = new StringBuilder(arr.length);
        for (var i = arr.length - 1; i >= 0; i--) {
            sb.append(arr[i]);
        }
        return sb.toString();
    }
}