class Solution {
    public String[] solution(String[] picture, int k) {
        var res = new String[picture.length * k];
        var rIdx = 0;

        for (var p : picture) {
            var sb = new StringBuilder(p.length() * k);
            for (var i = 0; i < p.length(); i++) {
                sb.repeat(p.charAt(i), k);
            }

            var str = sb.toString();
            for (var i = 0; i < k; i++) {
                res[rIdx++] = str;
            }
        }
        return res;
    }
}