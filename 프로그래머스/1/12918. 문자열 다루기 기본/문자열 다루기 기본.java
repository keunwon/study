class Solution {
    public boolean solution(String s) {
        if (s.length() != 4. && s.length() != 6) {
            return false;
        }
        
        for (var i = 0; i < s.length(); i++) {
            var c = s.charAt(i);
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}