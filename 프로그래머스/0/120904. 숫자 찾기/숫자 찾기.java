class Solution {
    public int solution(int num, int k) {
        var str = String.valueOf(num);
        for (var i = 0; i < str.length(); i++) {
            var c = str.charAt(i) - '0';
            if (c == k) return i + 1;
        }
        return -1;
    }
}