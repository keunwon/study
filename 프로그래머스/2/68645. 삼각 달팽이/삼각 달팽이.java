class Solution {
    public int[] solution(int n) {
        var r = -1;
        var c = 0;
        var x = 0;

        var res = new int[(n * (n + 1)) / 2];
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

                var idx = r * (r + 1) / 2 + c;
                res[idx] = ++x;
            }
        }
        return res;
    }
}