import java.util.Arrays;

import static java.util.Comparator.comparingInt;

class Solution {
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        var extIdx = getIndex(ext);
        var sortIdx = getIndex(sort_by);

        return Arrays.stream(data)
                .filter(d -> d[extIdx] < val_ext)
                .sorted(comparingInt(d -> d[sortIdx]))
                .toArray(int[][]::new);
    }

    private int getIndex(String str) {
        return switch (str) {
            case "code" -> 0;
            case "date" -> 1;
            case "maximum" -> 2;
            case "remain" -> 3;
            default -> -1;
        };
    }
}