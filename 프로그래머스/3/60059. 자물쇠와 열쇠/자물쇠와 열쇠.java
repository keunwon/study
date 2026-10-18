class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        var expandedLock = expanedLock(key.length, lock);

        for (var i = 0; i < 4; i++) {
            if (check(key, lock, expandedLock)) {
                return true;
            }
            rotate(key);
        }
        return false;
    }

    private void rotate(int[][] key) {
        var n = key.length;

        for (var i = 0; i < n; i++) {
            for (var j = i; j < n; j++) {
                var tmp = key[i][j];
                key[i][j] = key[j][i];
                key[j][i] = tmp;
            }
        }

        for (var i = 0; i < n; i++) {
            for (var j = 0; j < n / 2; j++) {
                var k = n - j - 1;
                var tmp = key[i][j];
                key[i][j] = key[i][k];
                key[i][k] = tmp;
            }
        }
    }

    private boolean check(int[][] key, int[][] lock, int[][] expandedLock) {
        var n = expandedLock.length - key.length;

        for (var i = 0; i < n; i++) {
            for (var j = 0; j < n; j++) {
                for (var k = 0; k < key.length; k++) {
                    for (var l = 0; l < key[0].length; l++) {
                        expandedLock[i + k][j + l] += key[k][l];
                    }
                }

                var valid = true;
                lo:
                for (var k = 0; k < lock.length; k++) {
                    for (var l = 0; l < lock[k].length; l++) {
                        var x = expandedLock[key.length + k - 1][key[0].length + l - 1];
                        if (x != 1) {
                            valid = false;
                            break lo;
                        }
                    }
                }
                if (valid) return true;

                for (var k = 0; k < key.length; k++) {
                    for (var l = 0; l < key[0].length; l++) {
                        expandedLock[i + k][j + l] -= key[k][l];
                    }
                }
            }
        }
        return false;
    }

    private int[][] expanedLock(int keySize, int[][] lock) {
        var n = lock.length;
        var size = keySize + (n * 2) - 2;
        var grid = new int[size][size];

        for (var i = 0; i < n; i++) {
            for (var j = 0; j < n; j++) {
                grid[keySize + i - 1][keySize + j - 1] = lock[i][j];
            }
        }
        return grid;
    }
}