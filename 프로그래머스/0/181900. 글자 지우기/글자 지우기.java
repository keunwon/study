class Solution {
    public String solution(String my_string, int[] indices) {
        var n = my_string.length();
        var removeArr = new boolean[n];

        for (var i : indices) {
            removeArr[i] = true;
        }

        var arr = new char[n - indices.length];
        var aIdx = -1;

        for (var i = 0; i < my_string.length(); i++) {
            if (!removeArr[i]) {
                arr[++aIdx] = my_string.charAt(i);
            }
        }
        return new String(arr);
    }
}