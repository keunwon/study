class Solution {
    public String solution(String s) {
        var m = s.length() / 2;
        var str = String.valueOf(s.charAt(m));
        return s.length() % 2 == 1 ? str : s.charAt(m - 1) + str;
    }
}