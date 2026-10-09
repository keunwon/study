class Solution {
    public String solution(int q, int r, String code) {
        var n = (code.length() - r + q - 1) / q;
        var arr = new char[n];
        
        for (var i = 0; i < n; i++) {
            arr[i] = code.charAt((i * q) + r);
        }
        return new String(arr);
    }
}