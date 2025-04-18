package algorithm.programmers;

public class Lesson250125 {
    public int solution(String[][] board, int h, int w) {
        var result = 0;
        var moves = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        for (var move : moves) {
            var nr = h + move[0];
            var nc = w + move[1];

            if (nr >= 0 && nc >= 0 && nr < board.length && nc < board[0].length) {
                if (board[h][w].equals(board[nr][nc])) ++result;
            }
        }
        return result;
    }
}
