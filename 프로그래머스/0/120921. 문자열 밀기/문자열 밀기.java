class Solution {
    public int solution(String A, String B) {
        if (A.equals(B)) return 0;
        
        var n = A.length();
        for (var i = 0; i < n - 1; i++) {
            var start = n - i - 1;
            var valid = true;
            
            for (var j = 0; j < n; j++) {
                if (A.charAt((start + j) % n) != B.charAt(j)) {
                    valid = false;
                    break;
                }
            }
            if (valid) return i + 1;
        }
        return -1;
    }
}