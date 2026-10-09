import java.util.HashMap;

class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        var map = new HashMap<String, Integer>(name.length);
        for (var i = 0; i < name.length; i++) {
            var n = name[i];
            var y = yearning[i];
            map.put(n, y);
        }

        var res = new int[photo.length];
        for (var i = 0; i < photo.length; i++) {
            for (var str : photo[i]) {
                res[i] += map.getOrDefault(str, 0);
            }
        }
        return res;
    }
}