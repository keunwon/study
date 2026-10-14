class Solution {
    public int solution(int[] rank, boolean[] attendance) {
        var a = -1;
        var b = -1;
        var c = -1;

        for (var i = 0; i < rank.length; i++) {
            if (!attendance[i]) continue;

            var r = rank[i];
            if (a == -1 || rank[a] > r) {
                c = b;
                b = a;
                a = i;
            } else if (b == -1 || rank[b] > r) {
                c = b;
                b = i;
            } else if (c == -1 || rank[c] > r) {
                c = i;
            }
        }
        return a * 10000 + b * 100 + c;
    }
}