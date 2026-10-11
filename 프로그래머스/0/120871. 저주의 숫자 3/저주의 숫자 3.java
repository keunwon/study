class Solution {
    public int solution(int n) {
        var res = 0;
        for (var i = 0; i < n; i++) {
            while (true) {
                if (check(++res)) break;
            }
        }
        return res;
    }

    private boolean check(int n) {
        if (n % 3 == 0) return false;

        while (n > 0) {
            if (n % 10 == 3) return false;
            n /= 10;
        }
        return true;
    }
}