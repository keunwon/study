class Solution {
    boolean solution(String s) {
        var p = 0;
        var y = 0;
        
        for (var i = 0; i <s.length(); i++) {
            var c = s.charAt(i);
            
            if (c == 'p' || c == 'P') ++p;
            else if (c == 'y' || c == 'Y') ++y;
        }
        return p == y;
    }
}