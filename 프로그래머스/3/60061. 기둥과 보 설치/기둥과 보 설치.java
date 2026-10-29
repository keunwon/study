import java.util.ArrayList;

class Solution {
    private boolean[][] pillars;
    private boolean[][] beams;

    public int[][] solution(int n, int[][] build_frame) {
        this.pillars = new boolean[n + 1][n + 1];
        this.beams = new boolean[n + 1][n + 1];

        for (var frame : build_frame) {
            var x = frame[0];
            var y = frame[1];
            var a = frame[2]; // 0: 기둥, 1: 보
            var b = frame[3]; // 0: 삭제, 1: 설치

            if (b == 0) {
                var grid = a == 0 ? pillars : beams;
                grid[y][x] = false;
                if (!valid()) {
                    grid[y][x] = true;
                }
            } else if (b == 1) {
                if (a == 0 && creatablePillar(y, x)) {
                    pillars[y][x] = true;
                } else if (a == 1 && creatableBeam(y, x)) {
                    beams[y][x] = true;
                }
            }
        }

        var res = new ArrayList<int[]>();
        for (var x = 0; x <= n; x++) {
            for (var y = 0; y <= n; y++) {
                if (pillars[y][x]) res.add(new int[] {x, y, 0});
                if (beams[y][x]) res.add(new int[] {x, y, 1});
            }
        }
        return res.toArray(int[][]::new);
    }

    private boolean valid() {
        var n = pillars.length;
        for (var y = 0; y < n; y++) {
            for (var x = 0; x < n; x++) {
                if (pillars[y][x] && !creatablePillar(y, x)) return false;
                if (beams[y][x] && !creatableBeam(y, x)) return false;
            }
        }
        return true;
    }

    // 보는 한쪽 끝 부분이 기둥 위에 있거나, 또는 양쪽 끝 부분이 다른 보와 동시에 연결되어 있어야 합니다.
    private boolean creatableBeam(int y, int x) {
        if ((y > 0 && pillars[y - 1][x]) || (pillars[y - 1][x + 1])) return true;
        else return (x > 0 && beams[y][x - 1]) && (x + 1 < beams[y].length && beams[y][x + 1]);
    }

    // 기둥은 바닥 위에 있거나 보의 한쪽 끝 부분 위에 있거나, 또는 다른 기둥 위에 있어야 합니다.
    private boolean creatablePillar(int y, int x) {
        if (y == 0) return true;
        else if (beams[y][x] || (x > 0 && beams[y][x - 1])) return true;
        else return pillars[y - 1][x];
    }
}