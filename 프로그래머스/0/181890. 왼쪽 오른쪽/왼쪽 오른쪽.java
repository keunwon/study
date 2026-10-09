import java.util.Arrays;

class Solution {
    public String[] solution(String[] str_list) {
        var n = str_list.length;
        for (var i = 0; i < n; i++) {
            var str = str_list[i];
            if (str.equals("l")) {
                return Arrays.copyOf(str_list, i);
            } else if (str.equals("r")) {
                return Arrays.copyOfRange(str_list, i + 1, n);
            }
        }
        return new String[]{};
    }
}