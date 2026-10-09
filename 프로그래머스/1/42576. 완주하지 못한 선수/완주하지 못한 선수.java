import java.util.HashMap;

class Solution {
    public String solution(String[] participant, String[] completion) {
        var map = new HashMap<String, Integer>(completion.length);
        for (var comp : completion) {
            map.put(comp, map.getOrDefault(comp, 0) + 1);
        }

        for (var p : participant) {
            var n = map.getOrDefault(p, 0);
            if (n == 0L) {
                return p;
            } else if (n == 1) {
                map.remove(p);
            } else {
                map.put(p, n - 1);
            }
        }
        return "";
    }
}