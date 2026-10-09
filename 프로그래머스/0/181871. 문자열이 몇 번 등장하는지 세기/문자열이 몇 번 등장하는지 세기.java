class Solution {
    public int solution(String myString, String pat) {
        var res = 0;
        for (var i = 0; i  <= myString.length() - pat.length(); i++) {
            if (pat.equals(myString.substring(i, i + pat.length()))) {
                ++res;
            }
        }
        return res;
    }
}