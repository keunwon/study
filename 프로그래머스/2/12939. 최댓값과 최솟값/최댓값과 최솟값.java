class Solution {
    public String solution(String s) {
        var tokens = s.split(" ");
        var min = Integer.parseInt(tokens[0]);
        var max = Integer.parseInt(tokens[0]);

        for (var i = 1; i < tokens.length; i++) {
            var x = Integer.parseInt(tokens[i]);
            min = Math.min(min, x);
            max = Math.max(max, x);
        }
        return min + " " + max;
    }
}