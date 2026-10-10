class Solution {
    public String solution(String cipher, int code) {
        var n = cipher.length() / code;
        var arr = new char[n];
        var cIdx = code - 1;

        for (var i = 0; i < n; i++) {
            arr[i] = cipher.charAt(cIdx);
            cIdx += code;
        }
        return new String(arr);
    }
}