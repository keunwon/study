class Solution {
    public int[] solution(String my_string) {
        var arr = new int[52];
        for (var i = 0; i < my_string.length(); i++) {
            var c = my_string.charAt(i);

            if (c >= 'a') {
                ++arr[c - 'a' + 26];
            } else {
                ++arr[c - 'A'];
            }
        }
        return arr;
    }
}