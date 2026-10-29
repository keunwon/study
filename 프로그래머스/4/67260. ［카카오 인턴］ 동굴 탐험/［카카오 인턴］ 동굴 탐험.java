import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solution {
    public boolean solution(int n, int[][] path, int[][] order) {
        var graph = new ArrayList<List<Integer>>(n);
        var startMap = HashMap.<Integer, Integer>newHashMap(order.length);
        var endMap = HashMap.<Integer, Integer>newHashMap(order.length);

        for (var i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (var p : path) {
            var u = p[0];
            var v = p[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        for (var o : order) {
            var s = o[0];
            var e = o[1];
            startMap.put(s, e);
            endMap.put(e, s);
        }

        if (endMap.containsKey(0)) {
            return false;
        }

        var q = new ArrayDeque<Integer>(n);
        var status = new char[n]; // ' ': default, 'V': visit, 'W': wait

        q.addLast(0);
        status[0] = 'V';

        while (!q.isEmpty()) {
            var cur = q.removeFirst();

            for (var adj : graph.get(cur)) {
                if (status[adj] != '\u0000') {
                    continue;
                }

                if (endMap.containsKey(adj)) {
                    var start = endMap.get(adj);
                    if (status[start] == 'V') {
                        status[adj] = 'V';
                        q.addLast(adj);
                    } else {
                        status[adj] = 'W';
                    }
                } else {
                    var end = startMap.get(adj);
                    if (end != null) {
                        if (status[end] == 'W') {
                            status[end] = 'V';
                            q.addLast(end);
                        }
                    }

                    status[adj] = 'V';
                    q.addLast(adj);
                }
            }
        }

        for (var i = 0; i < n; i++) {
            if (status[i] != 'V') return false;
        }
        return true;
    }
}