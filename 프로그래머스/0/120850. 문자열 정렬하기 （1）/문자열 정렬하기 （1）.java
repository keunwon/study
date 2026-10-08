import java.util.ArrayList;

class Solution {
    public int[] solution(String my_string) {
        var res = new ArrayList<Integer>();
        for (var i = 0; i < my_string.length(); i++) {
            var c = my_string.charAt(i);
            if (Character.isDigit(c)) {
                res.add(c - '0');
            }
        }
        return res.stream().mapToInt(Integer::intValue).sorted().toArray();
    }
}