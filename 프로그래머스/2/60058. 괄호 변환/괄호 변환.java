class Solution {
    public String solution(String p) {
        if (p.isBlank()) return "";

        var u = "";
        var v = "";
        var l = 0;
        var r = 0;

        for (var i = 0; i < p.length(); i++) {
            var c = p.charAt(i);

            if (c == '(') ++l;
            else ++r;

            if (l == r) {
                u = p.substring(0, i + 1);
                v = p.substring(i + 1);
                break;
            }
        }

        if (check(u)) {
            return u + solution(v);
        }

        var sb = new StringBuilder(p.length());
        sb.append('(').append(solution(v)).append(')');

        for (var i = 1; i < u.length() - 1; i++) {
            var c = u.charAt(i);
            if (c == '(') sb.append(')');
            else sb.append('(');
        }
        return sb.toString();
    }

    private boolean check(String str) {
        var l = 0;
        for (var i = 0; i < str.length(); i++) {
            var c = str.charAt(i);
            if (c == '(') {
                ++l;
            } else {
                if (l <= 0) return false;
                --l;
            }
        }
        return l == 0;
    }
}