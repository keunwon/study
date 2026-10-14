class Solution {
    public String solution(String code) {
        var sb = new StringBuilder(code.length());
        var mode = 0;

        for (var i = 0; i < code.length(); i++) {
            var c = code.charAt(i);
            if (mode == 0) {
                if (c == '1') mode = 1;
                else if (i % 2 == 0) sb.append(c);
            } else {
                if (c == '1') mode = 0;
                else if (i % 2 == 1) sb.append(c);
            }
        }
        return sb.isEmpty() ? "EMPTY" : sb.toString();
    }
}