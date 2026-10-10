class Solution {
    public int solution(int number, int limit, int power) {
        var count = new int[number + 1];
        for (var i = 1; i <= number; i++) {
            for (var j = i; j <= number; j += i) {
                ++count[j];
            }
        }

        var res = 0;
        for (var i = 1; i <= number; i++) {
            var x = count[i];
            res += limit >= x ? x : power;
        }
        return res;
    }
}