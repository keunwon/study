class Solution {
    public String solution(String X, String Y) {
        var countX = new int[10];
        var countY = new int[10];

        for (var i = 0; i < X.length(); i++) {
            var c = X.charAt(i);
            ++countX[c - '0'];
        }

        for (var i = 0; i < Y.length(); i++) {
            var c = Y.charAt(i);
            ++countY[c - '0'];
        }

        var sb = new StringBuilder();
        for (var i = 9; i >= 0; i--) {
            sb.repeat(String.valueOf(i), Math.min(countX[i], countY[i]));
        }

        if (sb.isEmpty()) {
            return "-1";
        } else if (sb.charAt(0) == '0') {
            return "0";
        } else {
            return sb.toString();
        }
    }
}