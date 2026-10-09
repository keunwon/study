class Solution {
    public String solution(String s) {
        var arr = s.toLowerCase().toCharArray();
        var idx = 0;
        var isSpace = true;

        while (idx < arr.length) {
            if (arr[idx] == ' ') {
                ++idx;
                isSpace = true;
                continue;
            }

            if (isSpace) {
                arr[idx] = Character.toUpperCase(arr[idx]);
                isSpace = false;
            }
            ++idx;
        }
        return new String(arr);
    }
}