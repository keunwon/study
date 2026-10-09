class Solution {
    public String solution(String my_string, int m, int c) {
        var n = (my_string.length() - c) / m + 1;
        var arr = new char[n];
        var mIdx = c - 1;
        
        for (var i = 0; i < n; i++) {
            arr[i] = my_string.charAt(mIdx);
            mIdx += m;
        }
        return new String(arr);
    }
}