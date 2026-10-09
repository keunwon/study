class Solution {
    public String solution(String my_string, String overwrite_string, int s) {
        var arr = my_string.toCharArray();
        for (var i = 0; i < overwrite_string.length(); i++) {
            arr[i + s] = overwrite_string.charAt(i);
        }
        return new String(arr);
    }
}