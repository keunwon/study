import java.util.Arrays;

class Solution {
    public String[] solution(String my_string) {
        var n = my_string.length();
        var res = new String[n];

        for (var i = 0; i < n; i++) {
            res[i] = my_string.substring(i);
        }

        Arrays.sort(res);
        return res;
    }
}