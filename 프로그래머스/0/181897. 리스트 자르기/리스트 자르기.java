import java.util.Arrays;

class Solution {
    public int[] solution(int n, int[] slicer, int[] num_list) {
        var a = slicer[0];
        var b = slicer[1];
        var c = slicer[2];

        return switch (n) {
            case 1 -> Arrays.copyOf(num_list, b + 1);
            case 2 -> Arrays.copyOfRange(num_list, a, num_list.length);
            case 3 -> Arrays.copyOfRange(num_list, a, b + 1);
            case 4 -> {
                var m = (b - a + c) / c;
                var res = new int[m];
                var nIdx = a;

                for (var i = 0; i < m; i++) {
                    res[i] = num_list[nIdx];
                    nIdx += c;
                }
                yield res;
            }
            default -> new int[] {-1};
        };
    }
}