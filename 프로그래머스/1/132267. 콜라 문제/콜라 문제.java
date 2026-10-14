class Solution {
    public int solution(int a, int b, int n) {
        var res = 0;
        while (n >= a) {
            var add = n / a * b;
            res += add;
            n = add + n % a;
        }
        return res;
    }
}