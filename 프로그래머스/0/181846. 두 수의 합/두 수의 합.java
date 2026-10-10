class Solution {
    public String solution(String a, String b) {
        var sb = new StringBuilder(Math.max(a.length(), b.length()) + 1);
        var idxA = a.length() - 1;
        var idxB = b.length() - 1;
        var carry = 0;

        while (idxA >= 0 || idxB >= 0 || carry > 0) {
            var n1 = idxA >= 0 ? a.charAt(idxA--) - '0' : 0;
            var n2 = idxB >= 0 ? b.charAt(idxB--) - '0' : 0;
            var sum = n1 + n2 + carry;

            sb.append(sum % 10);
            carry = sum / 10;
        }

        var str = sb.reverse().toString();
        return str.startsWith("0") ? "0" : str;
    }
}