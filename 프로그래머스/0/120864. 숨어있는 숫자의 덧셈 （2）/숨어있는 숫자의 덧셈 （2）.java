class Solution {
    public int solution(String my_string) {
        var res = 0;
        var x = 0;
        
        for (var i = 0; i < my_string.length(); i++) {
            var c = my_string.charAt(i);
            
            if (Character.isDigit(c)) {
                x = x * 10 + (c - '0');
            } else if (x > 0) {
                res += x;
                x = 0;
            }
        }
        res += x;
        
        return res;
    }
}