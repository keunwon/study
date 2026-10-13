class Solution {
    public String solution(String number, int k) {
        var idx = 0;
        var sb = new StringBuilder();

        for (var i = 0; i < number.length() - k; i++) {
            var max = -1;
            for (var j = idx; j <= i + k; j++) {
                var digit = number.charAt(j) - '0';
                if (digit > max) {
                    idx = j + 1;
                    max = digit;
                }
            }
            sb.append(max);
        }
        return sb.toString();
    }
}