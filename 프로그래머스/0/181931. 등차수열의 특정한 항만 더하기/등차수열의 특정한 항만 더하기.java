class Solution {
    public int solution(int a, int d, boolean[] included) {
        var res = 0;
        var num = a;

        for (boolean b : included) {
            if (b) res += num;
            num += d;
        }
        return res;
    }
}