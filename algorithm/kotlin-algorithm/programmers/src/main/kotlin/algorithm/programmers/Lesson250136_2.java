package algorithm.programmers;

public class Lesson250136_2 {
    public int solution(int[][] land) {
        var n = land.length;
        var m = land[0].length;

        var totalOils = new int[m];
        var dr = new int[]{-1, 1, 0, 0};
        var dc = new int[]{0, 0, -1, 1};

        var qLength = n * m;
        var qr = new int[qLength];
        var qc = new int[qLength];

        for (var i = 0; i < n; i++) {
            for (var j = 0; j < m; j++) {
                if (land[i][j] == 1) {
                    var size = 0;
                    var minCol = j;
                    var maxCol = j;

                    var head = 0;
                    var tail = 0;
                    qr[tail] = i;
                    qc[tail] = j;
                    ++tail;

                    land[i][j] = 0;

                    while (head < tail) {
                        var r = qr[head];
                        var c = qc[head];
                        ++head;
                        ++size;

                        if (c < minCol) minCol = c;
                        if (c > maxCol) maxCol = c;

                        for (var dir = 0; dir < 4; dir++) {
                            var nr = r + dr[dir];
                            var nc = c + dc[dir];

                            if (0 <= nr && 0 <= nc && nr < n && nc < m && land[nr][nc] == 1) {
                                land[nr][nc] = 0;
                                qr[tail] = nr;
                                qc[tail] = nc;
                                ++tail;
                            }
                        }
                    }

                    for (var col = minCol; col <= maxCol; ++col) {
                        totalOils[col] += size;
                    }
                }
            }
        }

        var maxOil = 0;
        for (var oil : totalOils) {
            if (oil > maxOil) maxOil = oil;
        }
        return maxOil;
    }
}
