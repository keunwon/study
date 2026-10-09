class Solution {
    public String solution(String str1, String str2) {
        var n = str1.length();
        var arr = new char[n * 2];
        
        for (var i = 0; i < n; i++) {
            arr[i * 2] = str1.charAt(i);
            arr[i * 2 + 1] = str2.charAt(i);
        }
        return new String(arr);
    }
}