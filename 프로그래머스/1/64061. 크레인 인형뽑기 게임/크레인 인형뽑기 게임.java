import java.util.ArrayDeque;

class Solution {
    public int solution(int[][] board, int[] moves) {
        var stk = new ArrayDeque<Integer>();
        var res = 0;

        for (int move : moves) {
            var m = move - 1;
            for (int[] arr : board) {
                var x = arr[m];
                if (x != 0) {
                    if (!stk.isEmpty() && stk.getLast() == x) {
                        stk.removeLast();
                        res += 2;
                    } else {
                        stk.addLast(x);
                    }
                    arr[m] = 0;
                    break;
                }
            }
        }
        return res;
    }
}