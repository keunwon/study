import java.util.TreeMap;

class Solution {
    public int[] solution(String[] operations) {
        var map = new TreeMap<Integer, Integer>();
        for (var op : operations) {
            if (op.charAt(0) == 'I') {
                var num = Integer.parseInt(op.substring(2));
                map.put(num, map.getOrDefault(num, 0) + 1);
            } else if (!map.isEmpty()) {
                var key = op.charAt(2) == '1' ? map.lastKey() : map.firstKey();
                var n = map.get(key);

                if (n == 1) {
                    map.remove(key);
                } else {
                    map.put(key, n - 1);
                }
            }
        }
        return map.isEmpty() ? new int[] {0, 0} : new int[] {map.lastKey(), map.firstKey()};
    }
}