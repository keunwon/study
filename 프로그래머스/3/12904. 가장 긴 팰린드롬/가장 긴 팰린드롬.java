class Solution {
    public int solution(String s) {
        var maxLen = 1;
        for (var i = 0; i < s.length(); i++) {
            maxLen = Math.max(maxLen, Math.max(lengthByPalindrome(s, i, i), lengthByPalindrome(s, i, i + 1)));
        }
        return maxLen;
    }

    private int lengthByPalindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length()) {
            if (s.charAt(left) != s.charAt(right)) {
                break;
            }
            --left;
            ++right;
        }
        return right - left - 1;
    }
}