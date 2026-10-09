class Solution {
    public int[] solution(String[] wallpaper) {
        var r1 = Integer.MAX_VALUE;
        var c1 = Integer.MAX_VALUE;
        var r2 = Integer.MIN_VALUE;
        var c2 = Integer.MIN_VALUE;

        for (var i = 0; i < wallpaper.length; i++) {
            for (var j = 0; j < wallpaper[i].length(); j++) {
                if (wallpaper[i].charAt(j) == '#') {
                    r1 = Math.min(r1, i);
                    c1 = Math.min(c1, j);
                    r2 = Math.max(r2, i);
                    c2 = Math.max(c2, j);
                }
            }
        }
        return new int[] {r1, c1, r2 + 1, c2 + 1};
    }
}