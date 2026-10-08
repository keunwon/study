import java.util.ArrayList;

class Solution {
    public String[] solution(String[] strArr) {
        var res = new ArrayList<String>();
        for (var str : strArr) {
            if (!str.contains("ad")) res.add(str);
        }
        return res.toArray(String[]::new);
    }
}