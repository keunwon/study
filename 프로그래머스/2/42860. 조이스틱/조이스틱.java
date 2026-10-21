class Solution {
    public int solution(String name) {
        var move = name.length() - 1;
        var change = 0;

        for (var i = 0; i < name.length(); i++) {
            var c = name.charAt(i);
            change += Math.min(c - 'A', 'Z' - c + 1);

            var idx = i + 1;
            while (idx < name.length() && name.charAt(idx) == 'A') {
                ++idx;
            }

            var n = name.length() - idx;
            move = Math.min(move, Math.min(n * 2 + i, i * 2 + n));
        }
        return move + change;
    }
}