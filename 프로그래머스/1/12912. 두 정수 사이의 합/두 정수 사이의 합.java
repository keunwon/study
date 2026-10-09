class Solution {
    public long solution(int a, int b) {
        if (a > b) {
            var tmp = a;
            a = b;
            b = tmp;
        }
        return (long) (b - a + 1) * (a + b) / 2;
    }
}