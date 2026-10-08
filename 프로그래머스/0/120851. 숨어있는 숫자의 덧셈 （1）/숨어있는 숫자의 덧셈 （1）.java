class Solution {
    public int solution(String my_string) {
        var res = 0;
        
        for (var i = 0; i < my_string.length(); i++) {
            var c = my_string.charAt(i);
            if (Character.isDigit(c)) {
                res += (c - '0');
            }
        }
        return res;
    }
}