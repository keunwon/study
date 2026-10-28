import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public int[] solution(String[] info, String[] query) {
        List<Integer>[] cost = new ArrayList[1 << 8];

        for (var i = 0; i < cost.length; i++) {
            cost[i] = new ArrayList<Integer>();
        }

        for (var s : info) {
            var tokens = s.split(" ");
            var point = Integer.parseInt(tokens[4]);
            var mask = createMaks(tokens[0], tokens[1], tokens[2], tokens[3]);

            for (var i = 0; i < 16; i++) {
                var curMask = mask;
                if ((i & 1) > 0) curMask = curMask & ~(3 << 6);
                if ((i & 2) > 0) curMask = curMask & ~(3 << 4);
                if ((i & 4) > 0) curMask = curMask & ~(3 << 2);
                if ((i & 8) > 0) curMask = curMask & ~(3);
                cost[curMask].add(point);
            }
        }

        for (var i = 0; i < (1 << 8); i++) {
            Collections.sort(cost[i]);
        }

        var res = new int[query.length];
        for (var i = 0; i < query.length; i++) {
            var tokens = query[i].replace(" and ", " ").split(" ");
            var point = Integer.parseInt(tokens[4]);
            var mask = createMaks(tokens[0], tokens[1], tokens[2], tokens[3]);
            var list = cost[mask];

            if (!list.isEmpty()) {
                var l = 0;
                var r = list.size() - 1;

                while (l <= r) {
                    var m = l + (r - l) / 2;

                    if (list.get(m) >= point) {
                        r = m - 1;
                    } else {
                        l = m + 1;
                    }
                }

                res[i] = list.size() - l;
            }
        }
        return res;
    }

    private int createMaks(String lang, String skill, String level, String food) {
        var langFlag = switch (lang) {
            case "cpp" -> 1;
            case "java" -> 2;
            case "python" -> 3;
            default -> 0;
        };
        var skillFlag = switch (skill) {
            case "backend" -> 1;
            case "frontend" -> 2;
            default -> 0;
        };
        var levelFlag = switch (level) {
            case "junior" -> 1;
            case "senior" -> 2;
            default -> 0;
        };
        var foodFlag = switch (food) {
            case "chicken" -> 1;
            case "pizza" -> 2;
            default -> 0;
        };
        return (langFlag << 6) | (skillFlag << 4) | (levelFlag << 2) | foodFlag;
    }
}