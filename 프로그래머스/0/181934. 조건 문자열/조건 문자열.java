class Solution {
    public int solution(String ineq, String eq, int n, int m) {
        var valid = true;
        if (ineq.equals("<")) {
            valid = eq.equals("=") ? n <= m : n < m;
        } else {
            valid = eq.equals("=") ? n >= m : n > m;
        }
        return valid ? 1 : 0;
    }
}