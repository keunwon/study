class Solution {
    public int[] solution(int n) {
        var r = -1;
        var c = 0;
        var x = 0;

        var size = 0;
        var floor = new int[n + 1];

        for (var i = 1; i <= n; i++) {
            size += i;
            floor[i] += i + floor[i - 1];
        }

        var res = new int[size];
        for (var i = 0; i < n; i++) {
            for (var j = i; j < n; j++) {
                switch (i % 3) {
                    case 0 -> ++r;
                    case 1 -> ++c;
                    case 2 -> {
                        --r;
                        --c;
                    }
                }
                res[floor[r] + c] = ++x;
            }
        }
        return res;
    }
}