class Solution {
    public String solution(String rsp) {
        var n = rsp.length();
        var arr = new char[n];

        for (var i = 0; i < n; i++) {
            arr[i] = switch (rsp.charAt(i)) {
                case '2' -> '0';
                case '0' -> '5';
                case '5' -> '2';
                default -> ' ';
            };
        }
        return new String(arr);
    }
}