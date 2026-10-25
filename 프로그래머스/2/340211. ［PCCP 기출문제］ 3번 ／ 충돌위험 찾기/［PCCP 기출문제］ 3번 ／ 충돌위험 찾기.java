import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solution {
    public int solution(int[][] points, int[][] routes) {
        var pointMap = new HashMap<Integer, Node>(points.length);

        for (var i = 0; i < points.length; i++) {
            var point = points[i];
            pointMap.put(i + 1, new Node(point[0], point[1]));
        }

        var history = new ArrayList<List<Node>>(routes.length);
        var maxSize = 0;

        for (var route : routes) {
            var list = new ArrayList<Node>();
            list.add(pointMap.get(route[0]));

            for (var i = 1; i < route.length; i++) {
                var p1 = pointMap.get(route[i - 1]);
                var p2 = pointMap.get(route[i]);

                var curR = p1.r;
                var curC = p1.c;

                while (curR != p2.r) {
                    if (curR < p2.r) ++curR;
                    else --curR;
                    list.add(new Node(curR, curC));
                }

                while (curC != p2.c) {
                    if (curC < p2.c) ++curC;
                    else --curC;
                    list.add(new Node(curR, curC));
                }
            }

            history.add(list);
            maxSize = Math.max(maxSize, list.size());
        }

        var res = 0;
        for (var i = 0; i < maxSize; i++) {
            var count = new int[10001];

            for (List<Node> nodes : history) {
                if (!nodes.isEmpty()) {
                    var node = nodes.removeFirst();
                    var idx = (node.r - 1) * 100 + node.c;
                    ++count[idx];
                }
            }

            for (int x : count) {
                if (x >= 2) ++res;
            }
        }
        return res;
    }

    private record Node(int r, int c) {}
}
