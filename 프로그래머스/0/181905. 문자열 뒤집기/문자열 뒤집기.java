class Solution {
    public String solution(String my_string, int s, int e) {
        var arr = my_string.toCharArray();
        while (s < e) {
            var tmp = arr[s];
            arr[s++] = arr[e];
            arr[e--] = tmp;
        }
        return new String(arr);
    }
}