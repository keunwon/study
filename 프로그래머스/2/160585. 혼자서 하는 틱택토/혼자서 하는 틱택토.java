class Solution {
    public int solution(String[] board) {
        var oCount = 0;
        var xCount = 0;

        for (var i = 0; i < 3; i++) {
            for (var j = 0; j < 3; j++) {
                switch (board[i].charAt(j)) {
                    case 'O' -> ++oCount;
                    case 'X' -> ++xCount;
                }
            }
        }

        if (oCount == xCount) {
            if (!check(board, 'O')) return 1;
            else return 0;
        } else if (oCount == xCount + 1) {
            if (!check(board, 'X')) return 1;
            else return 0;
        }
        return 0;
    }

    private boolean check(String[] board, char target) {
        for (var i = 0; i < 3; i++) {
            var count = 0;
            for (var j = 0; j < 3; j++) {
                if (board[i].charAt(j) == target) {
                    ++count;
                }
            }
            if (count == 3) return true;
        }

        for (var j = 0; j < 3; j++) {
            var count = 0;
            for (var i = 0; i < 3; i++) {
                if (board[i].charAt(j) == target) {
                    ++count;
                }
            }
            if (count == 3) return true;
        }

        if (board[0].charAt(0) == target && board[1].charAt(1) == target && board[2].charAt(2) == target) {
            return true;
        }
        return board[0].charAt(2) == target && board[1].charAt(1) == target && board[2].charAt(0) == target;
    }
}