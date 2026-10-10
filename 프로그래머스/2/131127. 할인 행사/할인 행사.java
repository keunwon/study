import java.util.Arrays;
import java.util.HashMap;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        var n = want.length;
        var map = new HashMap<String, Integer>(n);
        var count = new int[n];
        var size = 0;
        var res = 0;

        for (var i = 0; i < n; i++) {
            map.put(want[i], i);
        }

        for (var num : number) {
            size += num;
        }

        for (var i = 0; i < size; i++) {
            var id = map.getOrDefault(discount[i], -1);
            if (id > -1) ++count[id];
        }

        if (Arrays.equals(number, count)) ++res;

        for (var i = size; i < discount.length; i++) {
            var prevId = map.getOrDefault(discount[i - size], -1);
            var curId = map.getOrDefault(discount[i], -1);

            if (prevId > -1) --count[prevId];
            if (curId > -1) ++count[curId];

            if (Arrays.equals(number, count)) ++res;
        }
        return res;
    }
}