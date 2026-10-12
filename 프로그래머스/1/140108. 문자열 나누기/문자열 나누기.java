class Solution {
    public int solution(String s) {
        var x = ' ';
        var count = 0;
        var res = 0;
        
        for (var i = 0; i < s.length(); i++) {
            var c = s.charAt(i);
            
            if (count == 0) {
                ++res;
                x = c;
            }
            
            if (x == c) ++count;
            else --count;
        }
        return res;
    }
}