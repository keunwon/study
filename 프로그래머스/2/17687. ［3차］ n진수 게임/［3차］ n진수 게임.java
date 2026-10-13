class Solution {
    public String solution(int n, int t, int m, int p) {
        var minLength = t * m;
        var sb = new StringBuilder(minLength * 2);
        var num = 0;

        while (minLength > sb.length()) {
            sb.append(Integer.toString(num++, n).toUpperCase());
        }
        
        var res = new char[t];
        var sbIdx = p - 1;
        for (var i = 0; i < t; i++) {
            res[i] = sb.charAt(sbIdx);
            sbIdx += m;
        }
        return new String(res);
    }
}