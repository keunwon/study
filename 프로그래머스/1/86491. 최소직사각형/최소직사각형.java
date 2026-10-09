class Solution {
    public int solution(int[][] sizes) {
        var n1 = Integer.MIN_VALUE;
        var n2 = Integer.MIN_VALUE;

        for (var size : sizes) {
            var a = size[0];
            var b = size[1];
            if (a > b) {
                var tmp = a;
                a = b;
                b = tmp;
            }
            n1 = Math.max(n1, a);
            n2 = Math.max(n2, b);
        }
        return n1 * n2;
    }
}