import java.util.ArrayList;
import java.util.HashMap;

class Solution {
    public int[] solution(String msg) {
        var map = new HashMap<String, Integer>();
        for (var c = 'A'; c <= 'Z'; c++) {
            map.put(String.valueOf(c), map.size() + 1);
        }

        var res = new ArrayList<Integer>();
        var i = 0;
        while (i < msg.length()) {
            var sIdx = i;
            while (i < msg.length() && map.containsKey(msg.substring(sIdx, i + 1))) {
                ++i;
            }

            res.add(map.get(msg.substring(sIdx, i)));
            if (msg.length() >= i + 1) {
                var newWord = msg.substring(sIdx, i + 1);
                map.put(newWord, map.size() + 1);
            }
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }
}