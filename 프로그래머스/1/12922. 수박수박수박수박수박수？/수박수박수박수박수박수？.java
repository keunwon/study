class Solution {
    public String solution(int n) {
        var sb = new StringBuilder(n);
        sb.repeat("수박", n / 2);
        if (n % 2 == 1) sb.append('수');
        return sb.toString();
    }
}