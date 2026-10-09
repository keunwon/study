class Solution {
    public String solution(String myString, String pat) {
        var idx= myString.lastIndexOf(pat);
        return myString.substring(0, idx + pat.length());
    }
}