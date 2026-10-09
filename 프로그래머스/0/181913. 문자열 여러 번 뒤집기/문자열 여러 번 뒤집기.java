class Solution {
    public String solution(String my_string, int[][] queries) {
        var arr = my_string.toCharArray();
        for (var q : queries) {
            var s = q[0];
            var e = q[1];
            while (s < e) {
                var tmp = arr[s];
                arr[s++] = arr[e];
                arr[e--] = tmp;
            }
        }
        return new String(arr);
    }
}