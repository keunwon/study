class Solution {
    public int[] solution(String[] keyinput, int[] board) {
        var r = board[1] / 2;
        var c = board[0] / 2;

        for (var key : keyinput) {
            var tr = r;
            var tc = c;

            switch (key) {
                case "up" -> ++tr;
                case "down" -> --tr;
                case "left" -> --tc;
                case "right" -> ++tc;
            }

            if (tr >= 0 && tr < board[1] && tc >= 0 && tc < board[0]) {
                r = tr;
                c = tc;
            }
        }
        return new int[] {c - board[0] / 2, r - board[1] / 2};
    }
}