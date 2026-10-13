class Solution {
    public int[] solution(String[] park, String[] routes) {
        var dr = new int[] {-1, 1, 0, 0};
        var dc = new int[] {0, 0, -1, 1};
        var curR = 0;
        var curC = 0;

        for (var i = 0; i < park.length; i++) {
            for (var j = 0; j < park[i].length(); j++) {
                if (park[i].charAt(j) == 'S') {
                    curR = i;
                    curC = j;
                    break;
                }
            }
        }

        for (var route : routes) {
            var r = curR;
            var c = curC;
            var dir = switch (route.charAt(0)) {
                case 'N' -> 0;
                case 'S' -> 1;
                case 'W' -> 2;
                case 'E' -> 3;
                default -> -1;
            };
            var count = route.charAt(2) - '0';
            var valid = true;

            for (var i = 0; i < count; i++) {
                r += dr[dir];
                c += dc[dir];

                if (r < 0 || r >= park.length || c < 0 || c >= park[0].length() || park[r].charAt(c) == 'X') {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                curR = r;
                curC = c;
            }
        }
        return new int[] {curR, curC};
    }
}