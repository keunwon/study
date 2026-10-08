class Solution {
    public int solution(String[] s1, String[] s2) {
        var res = 0;
        for (var str1 : s1) {
            for (var str2: s2) {
                if (str1.equals(str2)) {
                    ++res;
                    break;
                }
            }
        }
        return res;
    }
}