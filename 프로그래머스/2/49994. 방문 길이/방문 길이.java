class Solution {
    public int solution(String dirs) {
        var dr = new int[] {-1, 0, 1, 0};
        var dc = new int[] {0, 1, 0, -1};
        var visited = new boolean[11][11][4];
        var r = 5;
        var c = 5;
        var distinctMove = 0;

        for (var i = 0; i < dirs.length(); i++) {
            var dir = switch (dirs.charAt(i)) {
                case 'U' -> 0;
                case 'R' -> 1;
                case 'D' -> 2;
                case 'L' -> 3;
                default -> -1;
            };
            var tr = r + dr[dir];
            var tc = c + dc[dir];

            if (tr >= 0 && tr < 11 && tc >= 0 && tc < 11) {
                if (!visited[tr][tc][dir]) {
                    visited[tr][tc][dir] = true;
                    visited[r][c][(dir + 2) % 4] = true;
                    ++distinctMove;
                }
                r = tr;
                c = tc;
            }
        }
        return distinctMove;
    }
}