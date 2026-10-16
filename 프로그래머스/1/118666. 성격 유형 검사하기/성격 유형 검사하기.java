class Solution {
    public String solution(String[] survey, int[] choices) {
        var types = new String[] {"RT", "CF", "JM", "AN"};
        var point = new int[8];
        var n = survey.length;

        for (var i = 0; i < n; i++) {
            var su = survey[i];
            var choice = choices[i];

            if (choice < 4) {
                var idx = getPointIdx(su.charAt(0));
                point[idx] += 4 - choice;
            } else if (choice > 4) {
                var idx = getPointIdx(su.charAt(1));
                point[idx] += choice - 4;
            }
        }

        var arr = new char[4];
        for (var i = 0; i < 4; i++) {
            var t = types[i];
            var idx1 = getPointIdx(t.charAt(0));
            var idx2 = getPointIdx(t.charAt(1));
            arr[i] = point[idx1] >= point[idx2] ? t.charAt(0) : t.charAt(1);
        }
        return new String(arr);
    }

    private int getPointIdx(char type) {
        return switch (type) {
            case 'R' -> 0;
            case 'T' -> 1;
            case 'C' -> 2;
            case 'F' -> 3;
            case 'J' -> 4;
            case 'M' -> 5;
            case 'A' -> 6;
            case 'N' -> 7;
            default -> -1;
        };
    }
}