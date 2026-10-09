class Solution {
    public int solution(int[][] dots) {
        var target = dots[0];
        var x = 0;
        var y = 0;

        for (var i = 1; i < dots.length; i++) {
            var dot = dots[i];
            if (target[0] == dot[0]) {
                x = Math.abs(target[1] - dot[1]);
            } else if (target[1] == dot[1]) {
                y = Math.abs(target[0] - dot[0]);
            }
        }
        return x * y;
    }
}