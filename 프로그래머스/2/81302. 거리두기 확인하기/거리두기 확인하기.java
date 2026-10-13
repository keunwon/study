import java.util.ArrayDeque;

class Solution {
    public int[] solution(String[][] places) {
        var dr = new int[] {-1, 1, 0, 0};
        var dc = new int[] {0, 0, -1, 1};
        var res = new int[places.length];

        for (var i = 0; i < places.length; i++) {
            var place = places[i];
            var m = place.length;
            var n = place[0].length();
            var q = new ArrayDeque<int[]>();
            var valid = true;

            lo:
            for (var j = 0; j < place.length; j++) {
                for (var k = 0; k < place[j].length(); k++) {
                    var p = place[j].charAt(k);
                    if (p != 'P') {
                        continue;
                    }

                    q.addLast(new int[] {j, k});

                    while (!q.isEmpty()) {
                        var cur = q.removeFirst();

                        for (var dir = 0; dir < 4; dir++) {
                            var r = cur[0] + dr[dir];
                            var c = cur[1] + dc[dir];
                            
                            if (r == j && c == k) {
                                continue;
                            }

                            if (r >= 0 && r < m && c >= 0 && c < n) {
                                var nextP = place[r].charAt(c);
                                var diff = Math.abs(r - j) + Math.abs(c - k);

                                if (diff <= 2 && nextP == 'P') {
                                    valid = false;
                                    break lo;
                                } else if (diff < 2 && nextP == 'O') {
                                    q.addLast(new int[] {r, c});
                                }
                            }
                        }
                    }
                }
            }

            res[i] = valid ? 1 : 0;
        }
        return res;
    }
}