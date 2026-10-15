import java.util.HashMap;
import java.util.Map;

import static java.util.Comparator.comparingInt;

class Solution {
    public int solution(int a, int b, int c, int d) {
        var countMap = new HashMap<Integer, Integer>();
        countMap.put(a, 1);
        countMap.put(b, countMap.getOrDefault(b, 0) + 1);
        countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        countMap.put(d, countMap.getOrDefault(d, 0) + 1);

        var list = countMap.entrySet().stream()
                .sorted(comparingInt(Map.Entry::getValue))
                .toList();

        return switch (list.size()) {
            case 1 -> list.getFirst().getKey() * 1111;
            case 2 -> {
                var e1 = list.get(0);
                var e2 = list.get(1);

                if (e2.getValue() == 3) {
                    var p = e2.getKey();
                    var q = e1.getKey();
                    yield (int) Math.pow(10 * p + q, 2);
                } else {
                    var p = e1.getKey();
                    var q = e2.getKey();
                    yield (p + q) * Math.abs(p - q);
                }
            }

            case 3 -> {
                var q = list.get(0).getKey();
                var r = list.get(1).getKey();
                yield q * r;
            }

            case 4 -> {
                var min = Integer.MAX_VALUE;
                for (var entry : list) {
                    min = Math.min(min, entry.getKey());
                }
                yield min;
            }

            default -> -1;
        };
    }
}