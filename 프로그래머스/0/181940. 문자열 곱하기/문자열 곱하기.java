class Solution {
    public String solution(String my_string, int k) {
        var sb = new StringBuilder(my_string.length() * k);
        sb.repeat(my_string, k);
        return sb.toString();
    }
}