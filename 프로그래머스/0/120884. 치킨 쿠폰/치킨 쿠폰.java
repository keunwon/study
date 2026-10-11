class Solution {
    public int solution(int chicken) {
        var res = 0;
        while (chicken >= 10) {
            var div = chicken / 10;
            res += div;
            chicken = div + chicken % 10;
        }
        return res;
    }
}