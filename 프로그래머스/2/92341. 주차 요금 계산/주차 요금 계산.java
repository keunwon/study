import java.util.HashMap;
import java.util.TreeMap;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        var tmpMap = new HashMap<String, Integer>();
        var parkMap = new TreeMap<String, Integer>();

        for (var r : records) {
            var tokens = r.split(" ");
            var minute = toMinute(tokens[0]);
            var id = tokens[1];
            var command = tokens[2];

            if (command.equals("IN")) {
                tmpMap.put(id, minute);
            } else if (command.equals("OUT")) {
                var startMinute = tmpMap.remove(id);
                var duration = minute - startMinute;
                parkMap.put(id, parkMap.getOrDefault(id, 0) + duration);
            }
        }

        var endMinute = toMinute("23:59");
        for (var entry : tmpMap.entrySet()) {
            var id = entry.getKey();
            var duration = endMinute - entry.getValue();
            parkMap.put(id, parkMap.getOrDefault(id, 0) + duration);
        }

        var baseMinute = fees[0];
        var basePrice = fees[1];
        var extraMinute = fees[2];
        var extraPrice = fees[3];
        var res = new int[parkMap.size()];
        var rIdx = 0;

        for (var m : parkMap.values()) {
            res[rIdx++] = baseMinute >= m
                    ? basePrice
                    : basePrice + (m - baseMinute + extraMinute - 1) / extraMinute * extraPrice;
        }
        return res;
    }

    private int toMinute(String time) {
        var h = (time.charAt(0) - '0') * 10 + (time.charAt(1) - '0');
        var m = (time.charAt(3) - '0') * 10 + (time.charAt(4) - '0');
        return h * 60 + m;
    }
}