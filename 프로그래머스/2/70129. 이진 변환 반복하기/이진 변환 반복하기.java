class Solution {
    public int[] solution(String s) {
        var res = new int[2];
        while (!s.equals("1")) {
            var zeroCount = 0;
            for (var i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '0') ++zeroCount;
            }

            s = Integer.toString(s.length() - zeroCount, 2);
            ++res[0];
            res[1] += zeroCount;
        }
        return res;
    }
}