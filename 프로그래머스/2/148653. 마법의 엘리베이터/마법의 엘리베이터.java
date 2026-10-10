class Solution {
    public int solution(int storey) {
        var res = 0;

        while (storey > 0) {
            var x = storey % 10;

            if (x < 5) {
                res += x;
            } else if (x > 5) {
                res += 10 - x;
                storey += 10;
            } else {
                res += 5;

                var nextX = storey / 10 % 10;
                if (nextX >= 5) storey += 10;
            }

            storey /= 10;
        }
        return res;
    }
}