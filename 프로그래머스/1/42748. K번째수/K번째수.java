import java.util.Arrays;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        var res = new int[commands.length];
        for (var i = 0; i < commands.length; i++) {
            var command = commands[i];
            var arr = Arrays.copyOfRange(array, command[0] - 1, command[1]);
            Arrays.sort(arr);
            res[i] = arr[command[2] - 1];
        }
        return res;
    }
}