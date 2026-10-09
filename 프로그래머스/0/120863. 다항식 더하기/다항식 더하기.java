class Solution {
    public String solution(String polynomial) {
        var xNum = 0;
        var num = 0;
        var tokens = polynomial.split(" \\+ ");

        for (var token : tokens) {
            if (token.endsWith("x")) {
                if (token.length() == 1) ++xNum;
                else xNum += Integer.parseInt(token.substring(0, token.length() - 1));
            } else {
                num += Integer.parseInt(token);
            }
        }

        var sb = new StringBuilder();
        if (xNum > 0) {
            if (xNum > 1) sb.append(xNum);
            sb.append('x');
        }

        if (num > 0) {
            if (!sb.isEmpty()) sb.append(" + ");
            sb.append(num);
        }
        return sb.toString();
    }
}