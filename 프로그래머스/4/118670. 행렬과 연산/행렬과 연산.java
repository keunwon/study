import java.util.ArrayDeque;

class Solution {
    public int[][] solution(int[][] rc, String[] operations) {
        var m = rc.length;
        var n = rc[0].length;
        var left = new ArrayDeque<Integer>();
        var mid = new ArrayDeque<ArrayDeque<Integer>>();
        var right = new ArrayDeque<Integer>();

        for (var i = 0; i < m; i++) {
            var deque = new ArrayDeque<Integer>();
            for (var j = 0; j < n; j++) {
                var x = rc[i][j];
                if (j == 0) {
                    left.addLast(x);
                } else if (j == n - 1) {
                    right.addLast(x);
                } else {
                    deque.addLast(x);
                }
            }
            mid.addLast(deque);
        }

        for (var op : operations) {
            switch (op) {
                case "ShiftRow" -> {
                    left.addFirst(left.removeLast());
                    mid.addFirst(mid.removeLast());
                    right.addFirst(right.removeLast());
                }

                case "Rotate" -> {
                    mid.getFirst().addFirst(left.removeFirst());
                    right.addFirst(mid.getFirst().removeLast());
                    mid.getLast().addLast(right.removeLast());
                    left.addLast(mid.getLast().removeFirst());
                }
            }
        }

        var res = new int[m][n];
        for (var i = 0; i < m; i++) {
            for (var j = 0; j < n; j++) {
                if (j == 0) {
                    res[i][j] = left.removeFirst();
                } else if (j == n - 1) {
                    res[i][j] = right.removeFirst();
                } else {
                    res[i][j] = mid.getFirst().removeFirst();
                }
            }
            mid.removeFirst();
        }
        return res;
    }
}